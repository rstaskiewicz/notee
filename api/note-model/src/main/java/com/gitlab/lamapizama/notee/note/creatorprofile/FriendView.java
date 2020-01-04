package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
class FriendView {
    @NonNull String friendEmail;
}
