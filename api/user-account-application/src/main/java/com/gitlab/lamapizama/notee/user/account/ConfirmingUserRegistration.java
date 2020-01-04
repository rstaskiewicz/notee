package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmationFailed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmed;
import com.gitlab.lamapizama.notee.user.verification.Token;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import com.gitlab.lamapizama.notee.user.verification.VerificationTokens;
import io.vavr.control.Either;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.gitlab.lamapizama.notee.commons.commands.Result.Rejection;
import static com.gitlab.lamapizama.notee.commons.commands.Result.Success;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;

@RequiredArgsConstructor
public class ConfirmingUserRegistration {

    private final UserAccounts userAccounts;
    private final VerificationTokens verificationTokens;

    public Result confirm(@NonNull ConfirmUserRegistrationCommand command) {
        UserAccount userAccount = find(command.getUserEmail());
        VerificationToken verificationToken = find(command.getToken());
        Either<UserAccountConfirmationFailed, UserAccountConfirmed> result = userAccount.confirm(verificationToken);
        return Match(result).of(
                Case($Left($()), this::publishEvent),
                Case($Right($()), this::publishEvent));
    }

    private Result publishEvent(UserAccountConfirmed userVerified) {
        userAccounts.publish(userVerified);
        return Success;
    }

    private Result publishEvent(UserAccountConfirmationFailed userVerificationFailed) {
        userAccounts.publish(userVerificationFailed);
        return Rejection;
    }

    private UserAccount find(UserEmail email) {
        return userAccounts.findBy(email)
                .getOrElseThrow(() -> new ResourceNotFoundException("User email does not exists:" + email.getEmail()));
    }

    private VerificationToken find(Token token) {
        return verificationTokens.findBy(token)
                .getOrElseThrow(() -> new ResourceNotFoundException("Token does not exists:" + token.getValue()));
    }
}

@Value
class ConfirmUserRegistrationCommand {
    @NonNull UserEmail userEmail;
    @NonNull Token token;
}
