package com.gitlab.lamapizama.notee.note.creator;

import io.vavr.collection.Set;
import lombok.Value;

@Value
class CreatorFriends {

    Set<Friend> friends;
}
