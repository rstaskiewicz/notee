package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
class UserVerificationTokens {

    static int MAX_NUMBER_OF_TOKENS  = 3;

    Set<UserVerificationToken> tokens;

    boolean contains(@NonNull VerificationToken verificationToken) {
        UserVerificationToken token = new UserVerificationToken(verificationToken.getToken());
        return tokens.contains(token);
    }

    int count() {
        return tokens.size();
    }
}
