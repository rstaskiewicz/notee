package com.gitlab.lamapizama.notee.user.account

import com.gitlab.lamapizama.notee.commons.commands.Result
import com.gitlab.lamapizama.notee.user.verification.Token
import com.gitlab.lamapizama.notee.user.verification.VerificationToken
import com.gitlab.lamapizama.notee.user.verification.VerificationTokens
import io.vavr.control.Option
import io.vavr.control.Try
import spock.lang.Specification

import static UserAccountFixture.anyUserEmail
import static UserAccountFixture.userAccount
import static VerificationTokenFixture.anyToken
import static VerificationTokenFixture.verificationToken

class ConfirmingUserRegistrationSpec extends Specification {

    UserAccounts userAccounts = Stub()
    VerificationTokens verificationTokens = Stub()

    def 'should successfully confirm user account registration'() {
        given:
            ConfirmingUserRegistration confirming = new ConfirmingUserRegistration(userAccounts, verificationTokens)
        and:
            Token verificationToken = persistedVerificationToken()
        and:
            UserEmail userEmail = persistedUserAccount(verificationToken)
        when:
            Try<Result> result = confirming.confirm(commandFrom(userEmail, verificationToken))
        then:
            result.isSuccess()
            result.get() == Result.Success
    }

    def 'should reject user account registration if one of the domain rules is broken (but should not fail)'() {
        given:
            ConfirmingUserRegistration confirming = new ConfirmingUserRegistration(userAccounts, verificationTokens)
        and:
            Token verificationToken = persistedVerificationToken()
        and:
            UserEmail userEmail = persistedUserAccount(verificationToken)
        and:
            Token anotherVerificationToken = persistedVerificationToken()
        when:
            Try<Result> result = confirming.confirm(commandFrom(userEmail, anotherVerificationToken))
        then:
            result.isSuccess()
            result.get() == Result.Rejection
    }

    def 'should fail if user account does not exists'() {
        given:
            ConfirmingUserRegistration confirming = new ConfirmingUserRegistration(userAccounts, verificationTokens)
        and:
            Token verificationToken = persistedVerificationToken()
        and:
            UserEmail userEmail = unknownUserAccount()
        when:
            Try<Result> result = confirming.confirm(commandFrom(userEmail, verificationToken))
        then:
            result.isFailure()
    }

    def 'should fail if token does not exists'() {
        given:
            ConfirmingUserRegistration confirming = new ConfirmingUserRegistration(userAccounts, verificationTokens)
        and:
            Token verificationToken = persistedVerificationToken()
        and:
            UserEmail userEmail = persistedUserAccount(verificationToken)
        when:
            Try<Result> result = confirming.confirm(commandFrom(userEmail, unknownToken()))
        then:
            result.isFailure()
    }

    def 'should fail if saving user account fails'() {
        given:
            ConfirmingUserRegistration confirming = new ConfirmingUserRegistration(userAccounts, verificationTokens)
        and:
            Token verificationToken = persistedVerificationToken()
        and:
            UserEmail userEmail = persistedUserAccountThatFailsOnSaving(verificationToken)
        when:
            Try<Result> result = confirming.confirm(commandFrom(userEmail, verificationToken))
        then:
            result.isFailure()
    }

    ConfirmUserRegistrationCommand commandFrom(UserEmail email, Token token) {
        return new ConfirmUserRegistrationCommand(email, token)
    }

    Token persistedVerificationToken() {
        Token token = anyToken()
        VerificationToken verificationToken = verificationToken(token)
        verificationTokens.findBy(verificationToken.token) >> Option.of(verificationToken)
        return token
    }

    UserEmail persistedUserAccount(Token token) {
        UserEmail userEmail = anyUserEmail()
        UserAccount account = userAccount(userEmail, token)
        userAccounts.findBy(userEmail) >> Option.of(account)
        userAccounts.publish(_ as UserAccountEvent) >> account
        return userEmail
    }


    UserEmail persistedUserAccountThatFailsOnSaving(Token token) {
        UserEmail userEmail = anyUserEmail()
        UserAccount account = userAccount(userEmail, token)
        userAccounts.findBy(userEmail) >> Option.of(account)
        userAccounts.publish(_ as UserAccountEvent) >> { throw new IllegalStateException() }
        return userEmail
    }

    UserEmail unknownUserAccount() {
        return anyUserEmail()
    }

    Token unknownToken() {
        return anyToken()
    }
}
