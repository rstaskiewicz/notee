package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import lombok.NonNull;
import lombok.Value;

@Value
class Possession {
    @NonNull NotebookId notebookId;
    @NonNull NotebookName name;
}
