package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;

public class NoteFactory {

    Note create(NoteId noteId, NoteName noteName, NoteType noteType, NotebookId notebookId) {
        return new Note(
                new NoteInformation(noteId, noteName, noteType),
                notebookId);
    }
}
