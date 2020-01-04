package com.gitlab.lamapizama.notee.user.account;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
class SentInvitations {

    Set<Invitation> invitations;

    boolean hasFor(@NonNull UserEmail userEmail) {
        return invitations.stream()
                .map(Invitation::getUserEmail)
                .anyMatch(email -> email.equals(userEmail));
    }
}
