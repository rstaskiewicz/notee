package com.gitlab.lamapizama.notee.user.account.persistance;

import com.gitlab.lamapizama.notee.user.verification.Token;
import com.gitlab.lamapizama.notee.user.verification.TokenExpiration;
import com.gitlab.lamapizama.notee.user.verification.VerificationToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String token;

    @Column(nullable = false)
    Instant expiration;

    public VerificationTokenEntity(String token, Instant expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public VerificationToken toDomainModel() {
        return new VerificationToken(new Token(token), new TokenExpiration(expiration));
    }
}
