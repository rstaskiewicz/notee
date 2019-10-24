package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.user.account.persistance.VerificationTokenDao;
import com.gitlab.lamapizama.notee.user.account.persistance.VerificationTokenEntity;
import com.gitlab.lamapizama.notee.user.verification.CreateVerificationToken;
import com.gitlab.lamapizama.notee.user.verification.Token;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import com.gitlab.lamapizama.notee.user.verification.VerificationTokens;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificationTokenDatabaseRepository implements CreateVerificationToken, VerificationTokens {

    private final VerificationTokenDao verificationTokenDao;

    @Override
    public void save(VerificationToken verificationToken) {
        verificationTokenDao.save(new VerificationTokenEntity(
                verificationToken.getToken().getValue(), verificationToken.getExpiration().getWhen()));
    }

    @Override
    public Option<VerificationToken> findBy(Token token) {
        return verificationTokenDao.findByToken(token.getValue())
                .map(VerificationTokenEntity::toDomainModel);
    }
}
