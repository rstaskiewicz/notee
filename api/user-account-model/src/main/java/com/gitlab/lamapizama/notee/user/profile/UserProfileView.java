package com.gitlab.lamapizama.notee.user.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileView {

    @NonNull String userEmail;
    @NonNull String fullName;
    @NonNull String avatarUrl;
    @NonNull @With Boolean isMyself = false;
    @NonNull @With Boolean isFriend = false;
}
