package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCommented;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCommentingFailed;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCreated;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteEdited;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteEditingFailed;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteRestored;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteRestoringFailed;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteTagged;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteTaggingFailed;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import io.vavr.API;
import io.vavr.collection.List;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

import java.util.UUID;

import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceSuccess;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;
import static io.vavr.collection.List.ofAll;

@Wither
@EqualsAndHashCode(of = "note")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Note {

    @NonNull
    NoteInformation note;

    @NonNull
    NoteContent noteContent;

    @NonNull
    NoteComments comments;

    @NonNull
    Tags tags;

    @NonNull FancyNoteContent content;

    public Either<NoteEditingFailed, NoteEdited> edit(FancyNoteContent content, CreatorId editorId) {
        return announceSuccess(NoteEdited.now(note.getNoteId(), editorId, content));
    }

    public Either<NoteCommentingFailed, NoteCommented> comment(CommentContent content, CreatorId commenterId) {
        return announceSuccess(NoteCommented.now(note.getNoteId(), commenterId, generateCommentId(), content));
    }

    public Either<NoteTaggingFailed, NoteTagged> tag(Tag tag, CreatorId taggerId) {
        return announceSuccess(NoteTagged.now(note.getNoteId(), taggerId, tag));
    }

    public Either<NoteRestoringFailed, NoteRestored> restore(NoteEdited noteEditedEvent, CreatorId reverserId) {
        return announceSuccess(NoteRestored.now(note.getNoteId(), noteEditedEvent.getNoteContent(), reverserId));
    }

    static Note rebuild(NoteId noteId, List<NoteEvent> events) {
        return ofAll(events)
                .foldLeft(initialState(noteId), Note::handle);
    }

    private static Note initialState(NoteId noteId) {
        return new NoteFactory().create(
                noteId,
                new NoteName(""),
                NoteType.Initialized,
                new CreatorId(""),
                new NotebookId(UUID.randomUUID()));
    }

    private Note handle(NoteEvent event) {
        return API.Match(event).of(
                Case($(instanceOf(NoteCreated.class)), this::handle),
                Case($(instanceOf(NoteEdited.class)), this::handle),
                Case($(instanceOf(NoteCommented.class)), this::handle),
                Case($(instanceOf(NoteTagged.class)), this::handle),
                Case($(instanceOf(NoteRestored.class)), this::handle));
    }

    private Note handle(NoteCreated event) {
        return this.withNote(new NoteInformation(
                new NoteId(event.getNoteId()),
                new NoteName(event.getNoteName()),
                event.getNoteType(),
                new CreatorId(event.getCreatorId()),
                new NotebookId(event.getNotebookId())));
    }

    private Note handle(NoteEdited event) {
        return this.withContent(event.getNoteContent());
    }

    private Note handle(NoteCommented event) {
        return this.withComments(comments.addComment(
                new Comment(
                        new CommentId(event.getCommentId()),
                        new CommentContent(event.getCommentContent()),
                        new CreatorId(event.getCreatorId()))));
    }

    private Note handle(NoteTagged event) {
        return this.withTags(tags.add(new Tag(event.getTag())));
    }

    private Note handle(NoteRestored event) {
        return this.withContent((event.getRestoredContent()));
    }

    private CommentId generateCommentId() {
        return new CommentId(comments.generateNewId());
    }

    CreatorId owner() {
        return note.getOwner();
    }

    boolean isPrivate() {
        return note.isPrivate();
    }
}
