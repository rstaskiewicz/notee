package com.gitlab.lamapizama.notee.user.verification;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class Token {
    @NonNull String value;

    public static Token generate() {
        return new Token(UUID.randomUUID().toString());
    }
}
