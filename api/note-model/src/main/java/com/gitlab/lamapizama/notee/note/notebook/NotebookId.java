package com.gitlab.lamapizama.notee.note.notebook;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class NotebookId {
    @NonNull UUID id;
}
