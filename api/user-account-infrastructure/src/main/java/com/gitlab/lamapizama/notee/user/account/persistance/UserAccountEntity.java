package com.gitlab.lamapizama.notee.user.account.persistance;

import com.gitlab.lamapizama.notee.user.account.EncodedPassword;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.UserAccountConfirmed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.VerificationTokenAssigned;
import com.gitlab.lamapizama.notee.user.account.UserEmail;
import com.gitlab.lamapizama.notee.user.account.Username;
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

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

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

    @OneToMany(mappedBy = "userAccount")
    Set<UserVerificationTokenEntity> verificationTokens = new HashSet<>();

    public UserAccountEntity(UserEmail userEmail, Username username, EncodedPassword password) {
        this.email = userEmail.getEmail();
        this.username = username.getUsername();
        this.password = password.getPassword();
    }

    public UserAccountEntity handle(UserAccountEvent event) {
        return API.Match(event).of(
                Case($(instanceOf(VerificationTokenAssigned.class)), this::handle),
                Case($(instanceOf(UserAccountConfirmed.class)), this::handle)
        );
    }

    private UserAccountEntity handle(VerificationTokenAssigned event) {
        verificationTokens.add(new UserVerificationTokenEntity(event.getToken().getValue(), this));
        return this;
    }

    private UserAccountEntity handle(UserAccountConfirmed event) {
        confirmed = true;
        return this;
    }
}
