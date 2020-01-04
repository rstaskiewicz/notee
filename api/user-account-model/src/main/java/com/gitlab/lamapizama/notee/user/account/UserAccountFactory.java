package com.gitlab.lamapizama.notee.user.account;

import java.util.Set;

class UserAccountFactory {

    UserAccount create(UserEmail userEmail,
                       Username username,
                       EncodedPassword encodedPassword,
                       boolean confirmed,
                       Set<UserVerificationToken> userVerificationTokens,
                       Set<Invitation> sentInvitations,
                       Set<Invitation> receivedInvitations) {
        return new UserAccount(
                new UserInformation(userEmail, username, encodedPassword, confirmed),
                new UserVerificationTokens(userVerificationTokens),
                new SentInvitations(sentInvitations),
                new ReceivedInvitations(receivedInvitations),
                VerificationPolicy.allCurrentPolicies(),
                TokenAssigningPolicy.allCurrentPolicies());
    }
}
