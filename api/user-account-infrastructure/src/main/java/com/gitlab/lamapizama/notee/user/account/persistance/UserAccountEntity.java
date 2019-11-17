package com.gitlab.lamapizama.notee.user.account.persistance;

import com.gitlab.lamapizama.notee.user.account.UserAccountEvent;
import io.vavr.API;
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

import static com.gitlab.lamapizama.notee.user.account.UserAccountEvent.*;
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

    @OneToMany(mappedBy = "userAccount", cascade = ALL)
    Set<UserVerificationTokenEntity> verificationTokens = new HashSet<>();

    public UserAccountEntity(String userEmail, String username, String password) {
        this.email = userEmail;
        this.username = username;
        this.password = password;
    }

    public UserAccountEntity handle(UserAccountEvent event) {
        return API.Match(event).of(
                API.Case(API.$(instanceOf(VerificationTokenAssigned.class)), this::handle),
                API.Case(API.$(instanceOf(UserAccountConfirmed.class)), this::handle)
        );
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
