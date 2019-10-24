package com.gitlab.lamapizama.notee.user.account;

import java.util.Set;

class UserAccountFactory {

    UserAccount create(UserEmail userEmail,
                              Username username,
                              EncodedPassword encodedPassword,
                              boolean confirmed,
                              Set<UserVerificationToken> userVerificationTokens) {
        return new UserAccount(
                new UserInformation(userEmail, username, encodedPassword, confirmed),
                new UserVerificationTokens(userVerificationTokens),
                VerificationPolicy.allCurrentPolicies(),
                TokenAssigningPolicy.allCurrentPolicies());
    }
}
