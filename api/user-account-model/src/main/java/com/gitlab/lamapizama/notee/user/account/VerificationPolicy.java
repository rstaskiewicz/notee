package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.policies.Allowance;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.control.Either;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

interface VerificationPolicy extends Function2<VerificationToken, UserAccount, Either<Rejection, Allowance>> {

    VerificationPolicy userMustHaveVerificationTokenAssignedPolicy = (VerificationToken token, UserAccount account) -> {
        if (!account.has(token)) {
            return left(Rejection.withReason("The token has not been assigned to the user account"));
        }
        return right(new Allowance());
    };

    VerificationPolicy expiredTokenRejectionPolicy = (VerificationToken token, UserAccount account) -> {
        if (token.isExpired()) {
            return left(Rejection.withReason("Verification is not possible due to the expiration of the token"));
        }
        return right(new Allowance());
    };

    VerificationPolicy userAccountIsNotConfirmedYetPolicy = (VerificationToken token, UserAccount account) -> {
        if (account.isVerified()) {
            return left(Rejection.withReason("The user account has already been verified"));
        }
        return right(new Allowance());
    };

    static List<VerificationPolicy> allCurrentPolicies() {
        return List.of(
                userMustHaveVerificationTokenAssignedPolicy,
                expiredTokenRejectionPolicy,
                userAccountIsNotConfirmedYetPolicy);
    }
}
