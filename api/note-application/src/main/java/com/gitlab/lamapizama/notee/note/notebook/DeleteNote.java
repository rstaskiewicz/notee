package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.note.NoteId;
import lombok.NonNull;
import lombok.Value;

@Value
public class DeleteNote {
    @NonNull NotebookId notebookId;
    @NonNull NoteId noteId;
}
