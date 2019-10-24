package com.gitlab.lamapizama.notee.user.account

import com.gitlab.lamapizama.notee.user.verification.Token

class UserAccountFixture {

    static final String EXAMPLE_EMAIL = 'example@mail.com'
    static final String USERNAME = 'User Name'
    static final String PASSWORD = '#$%EncodedPassword#$%'

    static UserEmail anyUserEmail() {
        return new UserEmail(EXAMPLE_EMAIL)
    }

    static UserAccount userAccount(UserEmail userEmail, Token token) {
        return new UserAccount(
                userInformation(userEmail),
                userVerificationTokens(token),
                VerificationPolicy.allCurrentPolicies(),
                TokenAssigningPolicy.allCurrentPolicies()
        )
    }

    static UserInformation userInformation(UserEmail userEmail) {
        return new UserInformation(userEmail, new Username(USERNAME), new EncodedPassword(PASSWORD), false)
    }

    static UserVerificationTokens userVerificationTokens(Token token) {
        Set tokens = [new UserVerificationToken(token)]
        return new UserVerificationTokens(tokens)
    }
}

