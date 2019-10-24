package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmationFailed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssignationFailed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssigned;
import com.gitlab.lamapizama.notee.user.verification.Token;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceFailure;
import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceSuccess;


@AllArgsConstructor
public class UserAccount {

    @NonNull
    private final UserInformation user;

    @NonNull
    private final UserVerificationTokens verificationTokens;

    @NonNull
    private final List<VerificationPolicy> verificationPolicies;

    @NonNull
    private final List<TokenAssigningPolicy> tokenAssigningPolicies;

    public Either<VerificationTokenAssignationFailed, VerificationTokenAssigned> assignVerificationToken(
            RegistrationContextPath contextPath) {
        Option<Rejection> rejection = verificationTokenCanBeAssigned();
        if (rejection.isEmpty()) {
            return announceSuccess(new VerificationTokenAssigned(user.getEmail(), Token.generate(), contextPath));
        }
        return announceFailure(new VerificationTokenAssignationFailed(rejection.get().getReason(), user.getEmail()));
    }

    public Either<UserAccountConfirmationFailed, UserAccountConfirmed> confirm(VerificationToken verificationToken) {
        Option<Rejection> rejection = userAccountCanBeConfirmed(verificationToken);
        if (rejection.isEmpty()) {
            return announceSuccess(new UserAccountConfirmed(user.getEmail()));
        }
        return announceFailure(new UserAccountConfirmationFailed(rejection.get().getReason(), user.getEmail()));
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
}
