package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.FriendAccepted;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.FriendInvited;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.InvitationAccepted;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.InvitationReceived;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class UserAccountEventHandler {

    private final UserAccounts userAccounts;

    @EventListener
    void handle(FriendInvited event) {
        userAccounts.publish(InvitationReceived.now(
                new UserEmail(event.getFriendEmail()),
                new UserEmail(event.getUserEmail())));
    }

    @EventListener
    void handle(FriendAccepted event) {
        userAccounts.publish(InvitationAccepted.now(
                new UserEmail(event.getInvitingEmail()),
                new UserEmail(event.getUserEmail())));
    }
}
