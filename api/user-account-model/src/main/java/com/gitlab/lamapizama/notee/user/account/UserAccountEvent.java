package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.events.DomainEvent;
import com.gitlab.lamapizama.notee.user.verification.Token;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

public interface UserAccountEvent extends DomainEvent {

    default UserEmail userEmail() {
        return getUserEmail();
    }

    UserEmail getUserEmail();

    @Value
    class UserAccountRegistered implements UserAccountEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant timestamp = Instant.now();
        @NonNull UserEmail userEmail;
        @NonNull Username username;
        @NonNull EncodedPassword password;
        @NonNull RegistrationContextPath contextPath;
    }

    @Value
    class UserAccountConfirmed implements UserAccountEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant timestamp = Instant.now();
        @NonNull UserEmail userEmail;
    }

    @Value
    class UserAccountConfirmationFailed implements UserAccountEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant timestamp = Instant.now();
        @NonNull String reason;
        @NonNull UserEmail userEmail;
    }

    @Value
    class VerificationTokenAssigned implements UserAccountEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant timestamp = Instant.now();
        @NonNull UserEmail userEmail;
        @NonNull Token token;
        @NonNull RegistrationContextPath contextPath;
    }

    @Value
    class VerificationTokenAssignationFailed implements UserAccountEvent {
        @NonNull UUID eventId = UUID.randomUUID();
        @NonNull Instant timestamp = Instant.now();
        @NonNull String reason;
        @NonNull UserEmail userEmail;
    }
}
