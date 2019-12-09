package com.gitlab.lamapizama.notee.user.account.persistance;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmed;
import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssigned;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    boolean confirmed;

    String avatarUrl;

    @OneToMany(mappedBy = "userAccount", cascade = ALL)
    Set<UserVerificationTokenEntity> verificationTokens = new HashSet<>();

    public UserAccountEntity(String userEmail, String username, String password) {
        this.email = userEmail;
        this.username = username;
        this.password = password;
    }

    public UserAccountEntity handle(UserAccountEvent event) {
        return Match(event).of(
                Case($(instanceOf(VerificationTokenAssigned.class)), this::handle),
                Case($(instanceOf(UserAccountConfirmed.class)), this::handle));
    }

    private UserAccountEntity handle(VerificationTokenAssigned event) {
        verificationTokens.add(new UserVerificationTokenEntity(event.getToken(), this));
        return this;
    }

    private UserAccountEntity handle(UserAccountConfirmed event) {
        confirmed = true;
        return this;
    }
}
