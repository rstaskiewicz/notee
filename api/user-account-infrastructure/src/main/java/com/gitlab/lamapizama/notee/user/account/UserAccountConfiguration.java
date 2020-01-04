package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.events.DomainEvents;
import com.gitlab.lamapizama.notee.commons.events.EventSource;
import com.gitlab.lamapizama.notee.commons.events.KafkaDomainEventPublisher;
import com.gitlab.lamapizama.notee.user.account.persistance.UserAccountDao;
import com.gitlab.lamapizama.notee.user.account.persistance.VerificationTokenDao;
import com.gitlab.lamapizama.notee.user.verification.CreateVerificationToken;
import com.gitlab.lamapizama.notee.user.verification.VerificationTokens;
import lombok.RequiredArgsConstructor;
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
    InvitingFriend addingFriend(UserAccounts userAccounts, AuthenticationFacade authenticationFacade) {
        return new InvitingFriend(userAccounts, authenticationFacade);
    }

    @Bean
    UserAccountEventHandler userAccountEventHandler(UserAccounts userAccounts) {
        return new UserAccountEventHandler(userAccounts);
    }

    @Bean
    UserAccounts userAccounts(UserAccountDao userAccountDao,
                              DomainEvents domainEvents,
                              DomainModelMapper domainModelMapper,
                              EventSource source) {
        return new UserAccountDatabaseRepository(userAccountDao,
                domainEvents,
                domainModelMapper,
                new KafkaDomainEventPublisher(source));
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
