package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserAccountEventListener {

    private final AssigningVerificationToken assigningVerificationToken;

    @EventListener
    void handle(UserAccountRegistered event) {
        assigningVerificationToken.assign(new AssignVerificationTokenCommand(event.getUserEmail(), event.getContextPath()));
    }
}
