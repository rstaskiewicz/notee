package com.gitlab.lamapizama.notee;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
class SecurityConfig extends ResourceServerConfigurerAdapter {

    private final NoteeProperties properties;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers(
                        "/register/**",
                        "/confirm/**",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().cors();
    }

    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(properties.getAuth().getToken().getCheckTokenEndpointUrl());
        tokenService.setClientId(properties.getAuth().getJsClient().getClientId());
        tokenService.setClientSecret(properties.getAuth().getJsClient().getClientSecret());
        return tokenService;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }
}
