package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import com.gitlab.lamapizama.notee.note.notebook.DeleteNote;
import com.gitlab.lamapizama.notee.note.notebook.DeletingNote;
import com.gitlab.lamapizama.notee.note.notebook.EnterNote;
import com.gitlab.lamapizama.notee.note.notebook.EnteringNote;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
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
import java.util.List;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/notebooks/{notebookId}")
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookViews notebook;
    private final EnteringNote enteringNote;
    private final DeletingNote deletingNote;

    @GetMapping
    public ResponseEntity<NotebookModel> notebook(@PathVariable UUID notebookId) {
        return notebook.findBy(new NotebookId(notebookId))
                .map(notebookView -> ok(new NotebookModel(notebookId, notebookView.notebookName)))
                .getOrElse(notFound().build());
    }

    @GetMapping("/notes")
    ResponseEntity<CollectionModel<EntityModel<NoteSimpleView>>> findNotes(@PathVariable UUID notebookId) {
        List<EntityModel<NoteSimpleView>> notes = notebook.fetchNotesFor(new NotebookId(notebookId))
                .map(note -> noteWithLinkToSelf(notebookId, note))
                .collect(toList());
        return ok(new CollectionModel<>(notes,
                linkTo(methodOn(NotebookController.class).findNotes(notebookId))
                        .withSelfRel()
                        .andAffordance(afford(methodOn(NotebookController.class).createNote(notebookId, null)))));
    }

    @PostMapping("/notes")
    ResponseEntity<?> createNote(@PathVariable UUID notebookId, @RequestBody @Valid EnterNoteRequest request) {
        Try<Result> result = enteringNote.enter(new EnterNote(
                new NotebookId(notebookId),
                new NoteName(request.getNoteName()),
                request.getNoteType(),
                request.getContent()));
        return result
                .map(success -> ok().build())
                .getOrElse(status(INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/notes/{noteId}")
    ResponseEntity<?> deleteNote(@PathVariable UUID notebookId, @PathVariable UUID noteId) {
        Try<Result> result = deletingNote.delete(new DeleteNote(
                new NotebookId(notebookId),
                new NoteId(noteId)));
        return result
                .map(success -> noContent().build())
                .recover(r -> Match(r).of(Case($(instanceOf(ResourceNotFoundException.class)), notFound().build())))
                .getOrElse(status(INTERNAL_SERVER_ERROR).build());
    }

    private EntityModel<NoteSimpleView> noteWithLinkToSelf(UUID notebookId, NoteSimpleView note) {
        return new EntityModel<>(
                note,
                linkTo(methodOn(NoteController.class).note(note.noteId))
                        .withSelfRel()
                        .andAffordance(afford(methodOn(NotebookController.class).deleteNote(notebookId, note.noteId))));
    }
}

@Value
@EqualsAndHashCode(callSuper = false)
class NotebookModel extends RepresentationModel<NotebookModel> {

    UUID id;
    String name;

    public NotebookModel(UUID id, String name) {
        this.id = id;
        this.name = name;
        add(linkTo(methodOn(NotebookController.class).notebook(id)).withSelfRel());
        add(linkTo(methodOn(NotebookController.class).findNotes(id)).withRel("notes"));
    }
}
