package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.verification.Token;
import lombok.NonNull;
import lombok.Value;

@Value
class UserVerificationToken {
    @NonNull Token token;
}
