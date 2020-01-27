package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ActionForbiddenException;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.CommentContent;
import com.gitlab.lamapizama.notee.note.note.CommentNote;
import com.gitlab.lamapizama.notee.note.note.CommentingNote;
import com.gitlab.lamapizama.notee.note.note.EditNote;
import com.gitlab.lamapizama.notee.note.note.EditingNote;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteType;
import com.gitlab.lamapizama.notee.note.note.RestoreEventId;
import com.gitlab.lamapizama.notee.note.note.RestoreNote;
import com.gitlab.lamapizama.notee.note.note.RestoringNote;
import com.gitlab.lamapizama.notee.note.note.Tag;
import com.gitlab.lamapizama.notee.note.note.TagNote;
import com.gitlab.lamapizama.notee.note.note.TaggingNote;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.gitlab.lamapizama.notee.note.note.NoteType.Private;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/notes/{noteId}")
@RequiredArgsConstructor
class NoteController {

    private final NoteViews noteViews;
    private final EditingNote editingNote;
    private final CommentingNote commentingNote;
    private final TaggingNote taggingNote;
    private final RestoringNote revertingNote;
    private final CreatorViews creatorViews;
    private final AuthenticationFacade authentication;
    private final ContentSerializer contentSerializer;

    @GetMapping
    ResponseEntity<NoteModel> note(@PathVariable UUID noteId) {
        Option<NoteView> noteOpt = noteViews.findBy(new NoteId(noteId));
        if (noteOpt.isEmpty()) {
            return notFound().build();
        }
        NoteView noteView = noteOpt.get();
        noteView.setContent(contentSerializer.deserialize(noteView.noteContent));
        noteView.setTags(noteViews.findTagsFor(new NoteId(noteId)).asJava());
        noteView.setComments(noteViews.findCommentsFor(new NoteId(noteId)).asJava());
        if (noteView.noteType.equals(Private)
                && !authentication.isActionAllowed(noteView.createdBy, creatorViews.findFriendEmailsFor(new CreatorId(noteView.createdBy)).asJava())) {
            return status(FORBIDDEN).build();
        }
        return ok(new NoteModel(noteView));
    }

    @PostMapping
    ResponseEntity<?> editNote(@PathVariable UUID noteId, @RequestBody @Valid EditNoteRequest request) {
        Try<Result> result = editingNote.edit(new EditNote(
                new NoteId(noteId),
                request.getContent()));

        return result
                .map(success -> noContent().build())
                .recover(r -> Match(r).of(Case($(instanceOf(ActionForbiddenException.class)), status(FORBIDDEN).build())))
                .getOrElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/comments")
    ResponseEntity<CollectionModel<EntityModel<CommentView>>> findComments(@PathVariable UUID noteId) {
        List<EntityModel<CommentView>> versions = noteViews.findCommentsFor(new NoteId(noteId))
                .map(versionView -> commentWithLinkToSelf(noteId, versionView))
                .collect(toList());
        return ok(new CollectionModel<>(versions, linkTo(methodOn(NoteController.class).findComments(noteId)).withSelfRel()));
    }

    @PostMapping("/comments")
    ResponseEntity<?> commentNote(@PathVariable UUID noteId, @RequestBody @Valid CommentNoteRequest request) {
        Try<Result> result = commentingNote.comment(new CommentNote(
                new NoteId(noteId),
                new CommentContent(request.getCommentContent())));

        return result
                .map(success -> ok().build())
                .recover(r -> Match(r).of(Case($(instanceOf(ActionForbiddenException.class)), status(FORBIDDEN).build())))
                .getOrElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<?> deleteComment(@PathVariable UUID noteId, @PathVariable Integer commentId) {
        return null;
    }

    @GetMapping("/tags")
    ResponseEntity<CollectionModel<EntityModel<TagView>>> findTags(@PathVariable UUID noteId) {
        List<EntityModel<TagView>> tags = noteViews.findTagsFor(new NoteId(noteId))
                .map(tagView -> new EntityModel<>(tagView))
                .collect(toList());
        return ok(new CollectionModel<>(tags, linkTo(methodOn(NoteController.class).findTags(noteId)).withSelfRel()));
    }

    @PostMapping("/tags")
    ResponseEntity<?> addTag(@PathVariable UUID noteId, @RequestBody @Valid TagNoteRequest request) {
        Try<Result> result = taggingNote.tag(new TagNote(
                new NoteId(noteId),
                new Tag(request.getTagValue())));

        return result
                .map(success -> ok().build())
                .recover(r -> Match(r).of(Case($(instanceOf(ActionForbiddenException.class)), status(FORBIDDEN).build())))
                .getOrElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/activities")
    ResponseEntity<CollectionModel<EntityModel<ActivityView>>> findActivities(@PathVariable UUID noteId) {
        List<EntityModel<ActivityView>> activities = noteViews.findActivitiesFor(new NoteId(noteId))
                .map(activityView -> new EntityModel<>(activityView))
                .collect(toList());
        return ok(new CollectionModel<>(activities, linkTo(methodOn(NoteController.class).findActivities(noteId)).withSelfRel()));
    }

    @GetMapping("/versions")
    ResponseEntity<CollectionModel<EntityModel<NoteVersionView>>> findVersions(@PathVariable UUID noteId) {
        List<EntityModel<NoteVersionView>> versions = noteViews.findVersionsFor(new NoteId(noteId))
                .map(versionView -> versionWithLinkToSelf(noteId, versionView))
                .collect(toList());
        return ok(new CollectionModel<>(versions, linkTo(methodOn(NoteController.class).findVersions(noteId)).withSelfRel()));
    }

    @GetMapping("/versions/{versionId}")
    ResponseEntity<EntityModel<NoteVersionView>> findVersion(@PathVariable UUID noteId, @PathVariable UUID versionId) {
        return noteViews.findVersionsFor(new NoteId(noteId))
                .find(versionView -> versionView.versionId.equals(versionId))
                .map(versionView -> ok(versionWithLinkToSelf(noteId, versionView)))
                .getOrElse(notFound().build());
    }

    @PostMapping("/versions/{versionId}")
    ResponseEntity<?> restoreNote(@PathVariable UUID noteId, @PathVariable UUID eventId) {
        Try<Result> result = revertingNote.restore(new RestoreNote(
                new NoteId(noteId),
                new RestoreEventId(eventId)));

        return result
                .map(success -> ok().build())
                .recover(r -> Match(r).of(Case($(instanceOf(ResourceNotFoundException.class)), notFound().build())))
                .recover(r -> Match(r).of(Case($(instanceOf(ActionForbiddenException.class)), status(FORBIDDEN).build())))
                .getOrElse(ResponseEntity.status(INTERNAL_SERVER_ERROR).build());
    }

    private EntityModel<CommentView> commentWithLinkToSelf(UUID noteId, CommentView comment) {
        return new EntityModel<>(
                comment,
                linkTo(methodOn(NoteController.class).deleteComment(noteId, comment.commentId)).withRel("delete")
                        .andAffordance(afford(methodOn(NoteController.class).deleteComment(noteId, comment.commentId))));
    }

    private EntityModel<NoteVersionView> versionWithLinkToSelf(UUID noteId, NoteVersionView version) {
        return new EntityModel<>(
                version,
                linkTo(methodOn(NoteController.class).findVersion(noteId, version.versionId))
                        .withSelfRel()
                        .andAffordance(afford(methodOn(NoteController.class).restoreNote(noteId, version.versionId))));
    }
}

@Value
@EqualsAndHashCode(callSuper = false)
class NoteModel extends RepresentationModel<NoteModel> {

    UUID id;
    String name;
    NoteType type;
    FancyNoteContent content;
    String createdBy;
    Instant createdAt;
    String modifiedBy;
    Instant modifiedAt;
    List<TagView> tags;
    UUID notebookId;
    String notebookName;

    NoteModel(NoteView noteView) {
        this.id = noteView.noteId;
        this.name = noteView.noteName;
        this.type = noteView.noteType;
        this.content = noteView.content;
        this.createdBy = noteView.createdBy;
        this.modifiedBy = noteView.modifiedBy;
        this.createdAt = noteView.createdAt;
        this.modifiedAt = noteView.modifiedAt;
        this.tags = noteView.tags;
        this.notebookName = noteView.notebookName;
        this.notebookId = noteView.notebookId;

        add(linkTo(methodOn(NoteController.class).note(id)).withSelfRel()
                .andAffordance(afford(methodOn(NoteController.class).editNote(id, null))));
        add(linkTo(methodOn(NoteController.class).findComments(id)).withRel("comments"));
        add(linkTo(methodOn(NoteController.class).findTags(id)).withRel("tags"));
        add(linkTo(methodOn(NoteController.class).findActivities(id)).withRel("activities"));
        add(linkTo(methodOn(NoteController.class).findVersions(id)).withRel("versions"));
    }
}
