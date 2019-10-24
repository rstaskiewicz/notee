package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.policies.Allowance;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Either;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

interface TokenAssigningPolicy extends Function1<UserAccount, Either<Rejection, Allowance>> {

    TokenAssigningPolicy userAccountMaximumNumberOfVerificationTokensPolicy = (UserAccount userAccount) -> {
        if (userAccount.numberOfTokens() >= UserVerificationTokens.MAX_NUMBER_OF_TOKENS) {
            return left(Rejection.withReason("Cannot assign more than three verification tokens"));
        }
        return right(new Allowance());
    };

    static List<TokenAssigningPolicy> allCurrentPolicies() {
        return List.of(userAccountMaximumNumberOfVerificationTokensPolicy);
    }
}
