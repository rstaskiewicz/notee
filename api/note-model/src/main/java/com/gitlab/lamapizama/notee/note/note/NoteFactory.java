package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import io.vavr.collection.HashSet;

public class NoteFactory {

    Note create(NoteId noteId, NoteName noteName, NoteType noteType, NotebookId notebookId) {
        return new Note(
                new NoteInformation(noteId, noteName, noteType, notebookId),
                NoteContent.empty(),
                new NoteComments(HashSet.empty()),
                new Tags(HashSet.empty()));
    }
}
