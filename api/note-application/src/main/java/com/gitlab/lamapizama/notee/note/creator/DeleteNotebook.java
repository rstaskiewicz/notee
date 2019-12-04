package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import lombok.NonNull;
import lombok.Value;

@Value
public class DeleteNotebook {
    @NonNull CreatorId creatorId;
    @NonNull NotebookId notebookId;
}
