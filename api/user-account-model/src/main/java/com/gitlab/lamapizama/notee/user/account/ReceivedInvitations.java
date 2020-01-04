package com.gitlab.lamapizama.notee.user.account;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
class ReceivedInvitations {

    Set<Invitation> invitations;

    boolean hasFrom(@NonNull UserEmail userEmail) {
        return invitations.stream()
                .map(Invitation::getUserEmail)
                .anyMatch(email -> email.equals(userEmail));
    }

    boolean isAcceptedFrom(@NonNull UserEmail userEmail) {
        return invitations.stream()
                .filter(invitation -> invitation.getUserEmail().equals(userEmail))
                .findFirst()
                .map(Invitation::getAccepted)
                .get();
    }
}
