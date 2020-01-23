package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDashboardView {
    @NonNull UUID noteId;
    @NonNull String noteName;
    @NonNull String createdBy;
    @NonNull String modifiedBy;
    @NonNull Instant createdAt;
    @NonNull Instant modifiedAt;
    @NonNull @JsonIgnore String noteContent;
    @NonNull UUID notebookId;
    @NonNull String notebookName;
    @With String content;
}
