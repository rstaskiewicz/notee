package com.gitlab.lamapizama.notee.note.notebook;

import lombok.NonNull;
import lombok.Value;

@Value
class NotebookEntry {
    @NonNull NotebookId noteId;
}
