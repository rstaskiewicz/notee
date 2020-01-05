package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.user.verification.Token;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceFailure;
import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceSuccess;
import static com.gitlab.lamapizama.notee.commons.policies.Rejection.withReason;
import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.*;


@AllArgsConstructor
public class UserAccount {

    @NonNull
    private final UserInformation user;

    @NonNull
    private final UserVerificationTokens verificationTokens;

    @NonNull
    private final SentInvitations sentInvitations;

    @NonNull
    private final ReceivedInvitations receivedInvitations;

    @NonNull
    private final List<VerificationPolicy> verificationPolicies;

    @NonNull
    private final List<TokenAssigningPolicy> tokenAssigningPolicies;

    public Either<VerificationTokenAssignationFailed, VerificationTokenAssigned> assignVerificationToken(
            RegistrationContextPath contextPath) {
        Option<Rejection> rejection = verificationTokenCanBeAssigned();
        if (rejection.isEmpty()) {
            return announceSuccess(VerificationTokenAssigned.now(user.getEmail(), Token.generate(), contextPath));
        }
        return announceFailure(VerificationTokenAssignationFailed.now(rejection.get(), user.getEmail()));
    }

    public Either<UserAccountConfirmationFailed, UserAccountConfirmed> confirm(VerificationToken verificationToken) {
        Option<Rejection> rejection = userAccountCanBeConfirmed(verificationToken);
        if (rejection.isEmpty()) {
            return announceSuccess(UserAccountConfirmed.now(user.getEmail()));
        }
        return announceFailure(UserAccountConfirmationFailed.now(rejection.get(), user.getEmail()));
    }

    public Either<FriendInvitingFailed, FriendInvited> inviteFriend(UserAccount friendAccount) {
        UserEmail friendEmail = friendAccount.user.getEmail();
        if (!sentInvitations.hasFor(friendEmail) && !receivedInvitations.hasFrom(friendEmail)) {
            return announceSuccess(FriendInvited.now(user.getEmail(), friendEmail));
        }
        return announceFailure(FriendInvitingFailed.now(
                withReason("Friend already invited: " + friendEmail.getEmail()), user.getEmail()));
    }

    public Either<FriendAcceptingFailed, FriendAccepted> acceptFriend(UserAccount friendAccount) {
        UserEmail invitationEmail = friendAccount.user.getEmail();
        if (receivedInvitations.hasFrom(invitationEmail) && !receivedInvitations.isAcceptedFrom(invitationEmail)) {
            return announceSuccess(FriendAccepted.now(user.getEmail(), invitationEmail));
        }
        return announceFailure(FriendAcceptingFailed.now(
                withReason("No invitation from: " + invitationEmail.getEmail()), user.getEmail()));
    }

    private Option<Rejection> verificationTokenCanBeAssigned() {
        return tokenAssigningPolicies.toStream()
                .map(policy -> policy.apply( this))
                .find(Either::isLeft)
                .map(Either::getLeft);
    }

    private Option<Rejection> userAccountCanBeConfirmed(VerificationToken verificationToken) {
        return verificationPolicies.toStream()
                .map(policy -> policy.apply(verificationToken, this))
                .find(Either::isLeft)
                .map(Either::getLeft);
    }

    boolean has(VerificationToken verificationToken) {
        return verificationTokens.contains(verificationToken);
    }

    int numberOfTokens() {
        return verificationTokens.count();
    }

    boolean isVerified() {
        return user.isVerified();
    }
}
