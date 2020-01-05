package com.gitlab.lamapizama.notee.user.profile;

import com.gitlab.lamapizama.notee.user.account.UserEmail;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.NonNull;

public interface UserAccountViews {

    List<UserProfileView> findUserAccounts();

    Option<UserProfileView> findUserAccount(@NonNull UserEmail userEmail);

    List<InvitationView> findInvitationsFor(@NonNull UserEmail userEmail, boolean onlyNew);

    List<UserProfileView> findFriendsFor(@NonNull UserEmail userEmail);

    void markInvitationsAsVisitedFor(@NonNull UserEmail userEmail);
}
