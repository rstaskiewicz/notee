package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/notebooks/{notebookId}")
@RequiredArgsConstructor
public class NotebookController {

    private final EnteringNote enteringNote;
    private final DeletingNote deletingNote;

    @PostMapping("/notes")
    void createNotebook(@PathVariable UUID notebookId, @RequestBody @Valid EnterNoteRequest request) {
        enteringNote.enter(new EnterNote(
                new NotebookId(notebookId),
                new NoteName(request.getNoteName()),
                request.getNoteType()));
    }

    @DeleteMapping("/notes/{noteId}")
    void deleteNotebook(@PathVariable UUID notebookId, @PathVariable UUID noteId) {
        deletingNote.delete(new DeleteNote(
                new NotebookId(notebookId),
                new NoteId(noteId)));
    }
}
