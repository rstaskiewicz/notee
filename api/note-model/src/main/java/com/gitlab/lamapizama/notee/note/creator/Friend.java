package com.gitlab.lamapizama.notee.note.creator;

import lombok.NonNull;
import lombok.Value;

@Value
class Friend {
    @NonNull CreatorId creatorId;
}
