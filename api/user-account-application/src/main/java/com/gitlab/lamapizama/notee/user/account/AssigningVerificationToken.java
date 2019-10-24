package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssignationFailed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssigned;
import io.vavr.control.Either;
import io.vavr.control.Try;
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
public class AssigningVerificationToken {

    private final UserAccounts userAccounts;

    public Try<Result> assign(@NonNull AssignVerificationTokenCommand command) {
        return Try.of(() -> {
            UserAccount userAccount = find(command.getUserEmail());
            Either<VerificationTokenAssignationFailed, VerificationTokenAssigned> result = userAccount.assignVerificationToken(
                    command.getContextPath());
            return Match(result).of(
                    Case($Left($()), this::publishEvent),
                    Case($Right($()), this::publishEvent));
        });
    }

    private Result publishEvent(VerificationTokenAssigned verificationTokenAssigned) {
        userAccounts.publish(verificationTokenAssigned);
        return Success;
    }

    private Result publishEvent(VerificationTokenAssignationFailed verificationTokenAssignationFailed) {
        userAccounts.publish(verificationTokenAssignationFailed);
        return Rejection;
    }

    private UserAccount find(UserEmail email) {
        return userAccounts.findBy(email)
                .getOrElseThrow(() -> new ResourceNotFoundException("Item does not exists:" + email.getEmail()));
    }
}

@Value
class AssignVerificationTokenCommand {
    @NonNull UserEmail userEmail;
    @NonNull RegistrationContextPath contextPath;
}
