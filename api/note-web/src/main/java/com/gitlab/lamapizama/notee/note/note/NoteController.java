package com.gitlab.lamapizama.notee.note.note;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/notes/{noteId}")
@RequiredArgsConstructor
class NoteController {

    private final EditingNote editingNote;
    private final CommentingNote commentingNote;
    private final TaggingNote taggingNote;

    @PutMapping
    void editNoteBook(@PathVariable UUID noteId, @RequestBody @Valid EditNoteRequest request) {
        editingNote.edit(new EditNote(
                new NoteId(noteId),
                new NoteContent(request.getNoteContent())));
    }

    @PostMapping("/comments")
    void commentNote(@PathVariable UUID noteId, @RequestBody @Valid CommentNoteRequest request) {
        commentingNote.comment(new CommentNote(
                new NoteId(noteId),
                new CommentContent(request.getCommentContent())));
    }

    @PutMapping("/tags")
    void tagNote(@PathVariable UUID noteId, @RequestBody @Valid TagNoteRequest request) {
        taggingNote.tag(new TagNote(
                new NoteId(noteId),
                new Tags(request.getTags())));
    }
}
