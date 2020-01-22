package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NoteDashboardView {
    @NonNull UUID noteId;
    @NonNull String noteName;
    @NonNull String createdBy;
    @NonNull String modifiedBy;
    @NonNull Instant createdAt;
    @NonNull Instant modifiedAt;
    @NonNull String noteContent;
    @NonNull UUID notebookId;
    @NonNull String notebookName;
}
