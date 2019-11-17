package com.gitlab.lamapizama.notee.note.creator;

import lombok.NonNull;
import lombok.Value;

@Value
public class CreatorId {
    @NonNull String email;
}
