package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.events.DomainEvents;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountRegistered;
import com.gitlab.lamapizama.notee.user.account.persistance.UserAccountDao;
import com.gitlab.lamapizama.notee.user.account.persistance.UserAccountEntity;
import com.gitlab.lamapizama.notee.user.account.persistance.UserVerificationTokenDao;
import com.gitlab.lamapizama.notee.user.account.persistance.UserVerificationTokenEntity;
import com.gitlab.lamapizama.notee.user.verification.Token;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static java.util.stream.Collectors.toSet;

@Component
@RequiredArgsConstructor
public class UserAccountDatabaseRepository implements UserAccounts {

    private final UserAccountDao userAccountDao;
    private final UserVerificationTokenDao userVerificationTokenDao;
    private final DomainEvents events;
    private final DomainModelMapper domainModelMapper;

    @Override
    public Option<UserAccount> findBy(UserEmail userEmail) {
        return Option.of(userAccountDao.findByEmail(userEmail.getEmail()))
                .map(domainModelMapper::map);
    }

    @Override
    public UserAccount publish(UserAccountEvent event) {
        UserAccount result = Match(event).of(
                Case($(instanceOf(UserAccountRegistered.class)), this::createNewUserAccount),
                Case($(), this::handleNextEvent));
        events.publish(event);
        return result;
    }

    @Override
    public boolean containsWith(UserEmail userEmail) {
        return Option.of(userAccountDao.findByEmail(userEmail.getEmail())).isDefined();
    }

    private UserAccount createNewUserAccount(UserAccountRegistered event) {
        UserAccountEntity entity = userAccountDao.save(new UserAccountEntity(
                event.getUserEmail(), event.getUsername(), event.getPassword()));
        return domainModelMapper.map(entity);
    }

    private UserAccount handleNextEvent(UserAccountEvent event) {
        UserAccountEntity entity = userAccountDao.findByEmail(event.userEmail().getEmail());
        entity = entity.handle(event);
        userVerificationTokenDao.saveAll(entity.getVerificationTokens());
        entity = userAccountDao.save(entity);
        return domainModelMapper.map(entity);
    }
}

@RequiredArgsConstructor
class DomainModelMapper {

    private final UserAccountFactory userAccountFactory;

    UserAccount map(UserAccountEntity entity) {
        return userAccountFactory.create(
                new UserEmail(entity.getEmail()),
                new Username(entity.getUsername()),
                new EncodedPassword(entity.getPassword()),
                entity.isConfirmed(),
                mapUserVerificationTokens(entity.getVerificationTokens()));
    }

    private Set<UserVerificationToken> mapUserVerificationTokens(Set<UserVerificationTokenEntity> verificationTokens) {
        return verificationTokens.stream()
                .map(entity -> new UserVerificationToken(new Token(entity.getToken())))
                .collect(toSet());
    }
}
