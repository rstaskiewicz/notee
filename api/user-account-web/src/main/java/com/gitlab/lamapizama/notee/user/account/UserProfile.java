package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.profile.UserProfileView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class UserProfile {

    @NonNull String userEmail;
    @NonNull String fullName;
    @NonNull String avatarUrl;
    @NonNull @With Boolean isMyself = false;
    @NonNull @With Boolean isFriend = false;

    static UserProfile fromView(UserProfileView view) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserEmail(view.getUserEmail());
        userProfile.setFullName(view.getFullName());
        userProfile.setAvatarUrl(view.getAvatarUrl());
        userProfile.setIsMyself(view.getIsMyself());
        userProfile.setIsFriend(view.getIsFriend());
        return userProfile;
    }
}
