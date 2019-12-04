package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
class NoteVersionView {

    @NonNull UUID noteId;
    @NonNull String noteContent;
    @NonNull UUID versionId;
    @NonNull String versionBy;
    @NonNull Instant createdAt;
}
