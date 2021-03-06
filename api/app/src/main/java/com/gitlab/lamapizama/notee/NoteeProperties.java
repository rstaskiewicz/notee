package com.gitlab.lamapizama.notee;

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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class NoteeProperties {

    Auth auth;

    @Getter
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    static class Auth {

        Token token;
        JsClient jsClient;
        SwaggerClient swaggerClient;

        @Getter
        @RequiredArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
        static class Token {
            @NotBlank String checkTokenEndpointUrl;
            @NotBlank String authorizationEndpointUrl;
        }

        @Getter
        @RequiredArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
        static class JsClient {
            @NotBlank String clientId;
            @NotBlank String clientSecret;
        }

        @Getter
        @RequiredArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
        static class SwaggerClient {
            @NotBlank String clientId;
            @NotBlank String clientSecret;
        }
    }
}
