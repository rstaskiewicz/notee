package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import io.vavr.control.Try;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.gitlab.lamapizama.notee.commons.commands.Result.Success;
import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.*;

@RequiredArgsConstructor
public class RegisteringUserAccount {

    private final EncodeUserPassword encodeUserPassword;
    private final UserAccounts userAccounts;

    public Try<Result> register(@NonNull RegisterUserAccountCommand command) {
        return Try.of(() -> {
            EncodedPassword encodedPassword = encode(command.getPassword());
            return publishEvent(UserAccountRegistered.now(
                    command.getEmail(),
                    command.getUsername(),
                    encodedPassword,
                    command.getAvatar(),
                    command.getContextPath()));
        });
    }

    private Result publishEvent(UserAccountRegistered userAccountRegistered) {
        userAccounts.publish(userAccountRegistered);
        return Success;
    }

    private EncodedPassword encode(RawPassword password) {
        return encodeUserPassword.encode(password);
    }
}

@Value
@Builder
class RegisterUserAccountCommand {
    @NonNull UserEmail email;
    @NonNull Username username;
    @NonNull RawPassword password;
    @NonNull Avatar avatar;
    @NonNull RegistrationContextPath contextPath;
}
