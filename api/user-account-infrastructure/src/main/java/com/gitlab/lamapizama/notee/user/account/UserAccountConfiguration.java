package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.events.DomainEventPublisher;
import com.gitlab.lamapizama.notee.commons.events.DomainEvents;
import com.gitlab.lamapizama.notee.user.verification.CreateVerificationToken;
import com.gitlab.lamapizama.notee.user.verification.VerificationTokens;
import com.gitlab.lamapizama.notee.user.account.persistance.VerificationTokenDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
class UserAccountConfiguration {

    @Bean
    RegisteringUserAccount registeringUserAccount(EncodeUserPassword encodeUserPassword,
                                                  UserAccounts userAccounts) {
        return new RegisteringUserAccount(encodeUserPassword, userAccounts);
    }

    @Bean
    AssigningVerificationToken assigningVerificationToken(UserAccounts userAccounts) {
        return new AssigningVerificationToken(userAccounts);
    }

    @Bean
    ConfirmingUserRegistration confirmingUserRegistration(UserAccounts userAccounts,
                                                          VerificationTokens verificationTokens) {
        return new ConfirmingUserRegistration(userAccounts, verificationTokens);
    }

    @Bean
    CreateVerificationToken createVerificationToken(VerificationTokenDao verificationTokenDao) {
        return new VerificationTokenDatabaseRepository(verificationTokenDao);
    }

    @Bean
    VerificationTokens verificationTokens(VerificationTokenDao verificationTokenDao) {
        return new VerificationTokenDatabaseRepository(verificationTokenDao);
    }

    @Bean
    DomainEvents domainEvents(ApplicationEventPublisher applicationEventPublisher) {
        return new DomainEventPublisher(applicationEventPublisher);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DomainModelMapper domainModelMapper() {
        return new DomainModelMapper(new UserAccountFactory());
    }

    @Bean
    VerificationEventListener verificationEventListener(CreateVerificationToken createVerificationToken,
                                                        UserAccountMailSender userAccountMailSender) {
        return new VerificationEventListener(createVerificationToken, userAccountMailSender);
    }
}
