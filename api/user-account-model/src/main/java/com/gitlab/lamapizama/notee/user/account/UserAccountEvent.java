package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.events.DomainEvent;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.user.verification.Token;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

public interface UserAccountEvent extends DomainEvent<String> {

    default UserEmail userEmail() {
        return new UserEmail(getUserEmail());
    }

    String getUserEmail();

    default String getAggregateId() {
        return getUserEmail();
    }

    @Value
    class UserAccountRegistered implements UserAccountEvent {
        @NonNull Instant when;
        @NonNull String userEmail;
        @NonNull String username;
        @NonNull String password;
        @NonNull String avatarUrl;
        @NonNull String contextPath;

        static UserAccountRegistered now(UserEmail userEmail,
                                         Username username,
                                         EncodedPassword password,
                                         Avatar avatar,
                                         RegistrationContextPath path) {
            return new UserAccountRegistered(
                    Instant.now(),
                    userEmail.getEmail(),
                    username.getUsername(),
                    password.getPassword(),
                    avatar.getImageUrl(),
                    path.getContextPath());
        }
    }

    @Value
    class UserAccountConfirmed implements UserAccountEvent {
        @NonNull Instant when;
        @NonNull String userEmail;

        static UserAccountConfirmed now(UserEmail userEmail) {
            return new UserAccountConfirmed(Instant.now(), userEmail.getEmail());
        }
    }

    @Value
    class UserAccountConfirmationFailed implements UserAccountEvent {
        @NonNull Instant when;
        @NonNull String reason;
        @NonNull String userEmail;

        static UserAccountConfirmationFailed now(Rejection rejection, UserEmail userEmail) {
            return new UserAccountConfirmationFailed(Instant.now(), rejection.getReason(), userEmail.getEmail());
        }
    }

    @Value
    class VerificationTokenAssigned implements UserAccountEvent {
        @NonNull Instant when;
        @NonNull String userEmail;
        @NonNull String token;
        @NonNull String contextPath;

        static VerificationTokenAssigned now(UserEmail userEmail, Token token, RegistrationContextPath contextPath) {
            return new VerificationTokenAssigned(
                    Instant.now(),
                    userEmail.getEmail(),
                    token.getValue(),
                    contextPath.getContextPath());
        }
    }

    @Value
    class VerificationTokenAssignationFailed implements UserAccountEvent {
        @NonNull Instant when;
        @NonNull String reason;
        @NonNull String userEmail;

        static VerificationTokenAssignationFailed now(Rejection rejection, UserEmail userEmail) {
            return new VerificationTokenAssignationFailed(Instant.now(), rejection.getReason(), userEmail.getEmail());
        }
    }
}
