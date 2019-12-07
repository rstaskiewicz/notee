package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
class ActivityView {

    @NonNull UUID eventId;
    @NonNull String activityType;
    @NonNull UUID noteId;
    @NonNull String creatorId;
    @NonNull Instant occurredAt;
}
