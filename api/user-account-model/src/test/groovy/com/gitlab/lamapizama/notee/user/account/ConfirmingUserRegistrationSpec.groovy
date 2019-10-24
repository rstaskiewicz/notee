package com.gitlab.lamapizama.notee.user.account

import com.gitlab.lamapizama.notee.user.verification.VerificationToken
import io.vavr.control.Either
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmationFailed
import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmed
import static com.gitlab.lamapizama.notee.user.account.UserAccountFixture.userAccount
import static com.gitlab.lamapizama.notee.user.account.VerificationTokenFixture.anyToken
import static com.gitlab.lamapizama.notee.user.account.VerificationTokenFixture.expiredVerificationToken
import static com.gitlab.lamapizama.notee.user.account.VerificationTokenFixture.verificationToken

class ConfirmingUserRegistrationSpec extends Specification {

    def 'a user account can be confirmed if the given token has been assigned to him and is not expired'() {
        given:
            VerificationToken verificationToken = verificationToken()
        and:
            UserAccount userAccount = userAccount(verificationToken.token)
        when:
            Either<UserAccountConfirmationFailed, UserAccountConfirmed> confirmation = userAccount.confirm(verificationToken)
        then:
            confirmation.isRight()
    }

    def 'a user account cannot be confirmed after the token has expired'() {
        given:
            VerificationToken verificationToken = expiredVerificationToken()
        and:
            UserAccount userAccount = userAccount(verificationToken.token)
        when:
            Either<UserAccountConfirmationFailed, UserAccountConfirmed> confirmation = userAccount.confirm(verificationToken)
        then:
            confirmation.isLeft()
    }

    def 'a user account cannot be confirmed if the given token has not been assigned to him'() {
        given:
            VerificationToken verificationToken = verificationToken()
        and:
            UserAccount userAccount = userAccount(anyToken())
        when:
            Either<UserAccountConfirmationFailed, UserAccountConfirmed> confirmation = userAccount.confirm(verificationToken)
        then:
            confirmation.isLeft()
    }
}
