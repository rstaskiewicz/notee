package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import lombok.NonNull;
import lombok.Value;

@Value
class NotebookEntry {
    @NonNull NoteId id;
    @NonNull NoteName name;
}
