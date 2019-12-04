package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
class NotebookView {

    @NonNull UUID notebookId;
    @NonNull String notebookName;
}
