package com.gitlab.lamapizama.notee.user.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
@ConstructorBinding
@ConfigurationProperties("notee")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class NoteeProperties {

    Auth auth;

    public NoteeProperties(Auth auth) {
        this.auth = auth;
    }

    @Getter
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    static class Auth {

        Token token;
        Client client;

        @Getter
        @RequiredArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
        static class Token {
            @NotBlank String checkTokenEndpointUrl;
        }

        @Getter
        @RequiredArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
        static class Client {
            @NotBlank String clientId;
            @NotBlank String clientSecret;
        }
    }
}
