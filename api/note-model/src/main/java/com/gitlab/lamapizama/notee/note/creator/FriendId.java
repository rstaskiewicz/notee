package com.gitlab.lamapizama.notee.note.creator;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class FriendId {
    @NonNull UUID friendId;
}
