package com.gitlab.lamapizama.notee.note.notebook;

import lombok.NonNull;
import lombok.Value;

@Value
class NotebookInformation {
    @NonNull NotebookId notebookId;
    @NonNull NotebookName name;
}
