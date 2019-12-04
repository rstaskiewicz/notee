package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import lombok.NonNull;
import lombok.Value;

@Value
public class CreateNotebook {
    @NonNull CreatorId creatorId;
    @NonNull NotebookName notebookName;
}
