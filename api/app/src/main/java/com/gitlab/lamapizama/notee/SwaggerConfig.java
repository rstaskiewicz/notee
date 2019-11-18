package com.gitlab.lamapizama.notee;

import com.gitlab.lamapizama.notee.NoteeProperties.Auth.SwaggerClient;
import com.gitlab.lamapizama.notee.NoteeProperties.Auth.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
class SwaggerConfig {

    private final NoteeProperties properties;

    @Bean
    Docket api() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securityContexts(singletonList(securityContext()))
                .securitySchemes(singletonList(securityScheme()));
    }

    @Bean
    SecurityConfiguration security() {
        SwaggerClient client = properties.getAuth().getSwaggerClient();

        return SecurityConfigurationBuilder.builder()
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private SecurityScheme securityScheme() {
        Token token = properties.getAuth().getToken();
        SwaggerClient client = properties.getAuth().getSwaggerClient();

        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(token.getCheckTokenEndpointUrl(), "oauthtoken"))
                .tokenRequestEndpoint(new TokenRequestEndpoint(
                        token.getAuthorizationEndpointUrl(), client.getClientId(), client.getClientSecret()))
                .build();

        return new OAuthBuilder().name("notee_oauth")
                .grantTypes(singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")};
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(singletonList(new SecurityReference("notee_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }
}
