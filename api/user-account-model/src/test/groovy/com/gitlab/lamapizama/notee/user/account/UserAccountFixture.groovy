package com.gitlab.lamapizama.notee.user.account

import com.gitlab.lamapizama.notee.user.verification.Token

class UserAccountFixture {

    static final String EXAMPLE_EMAIL = 'example@mail.com'
    static final String USERNAME = 'User Name'
    static final String PASSWORD = '#$%EncodedPassword#$%'

    static UserEmail anyUserEmail() {
        return new UserEmail(EXAMPLE_EMAIL)
    }

    static UserAccount userAccount(Token token) {
        return new UserAccount(
                userInformation(anyUserEmail()),
                userVerificationTokens(token),
                sentInvitations(),
                receivedInvitations(),
                VerificationPolicy.allCurrentPolicies(),
                TokenAssigningPolicy.allCurrentPolicies())
    }

    static UserAccount userAccount(UserEmail userEmail, Token token) {
        return new UserAccount(
                userInformation(userEmail),
                userVerificationTokens(token),
                sentInvitations(),
                receivedInvitations(),
                VerificationPolicy.allCurrentPolicies(),
                TokenAssigningPolicy.allCurrentPolicies())
    }

    static UserAccount verifiedUserAccount(Token token) {
        return new UserAccount(
                verifiedUserInformation(anyUserEmail()),
                userVerificationTokens(token),
                sentInvitations(),
                receivedInvitations(),
                VerificationPolicy.allCurrentPolicies(),
                TokenAssigningPolicy.allCurrentPolicies())
    }

    static UserInformation userInformation(UserEmail userEmail) {
        return new UserInformation(userEmail, new Username(USERNAME), new EncodedPassword(PASSWORD), false)
    }

    static UserInformation verifiedUserInformation(UserEmail userEmail) {
        return new UserInformation(userEmail, new Username(USERNAME), new EncodedPassword(PASSWORD), true)
    }

    static UserVerificationTokens userVerificationTokens(Token token) {
        Set tokens = [new UserVerificationToken(token)]
        return new UserVerificationTokens(tokens)
    }

    static SentInvitations sentInvitations() {
        Set invitations = [new Invitation(anyUserEmail(), false)]
        return new SentInvitations(invitations)
    }

    static ReceivedInvitations receivedInvitations() {
        Set invitations = [new Invitation(anyUserEmail(), false)]
        return new ReceivedInvitations(invitations)
    }
}

