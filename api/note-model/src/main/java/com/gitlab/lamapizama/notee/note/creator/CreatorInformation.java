package com.gitlab.lamapizama.notee.note.creator;

import lombok.NonNull;
import lombok.Value;

@Value
class CreatorInformation {

    @NonNull
    CreatorId creatorId;

    @NonNull
    CreatorType type;

    boolean isRegular() {
        return type.equals(CreatorType.Regular);
    }
}
