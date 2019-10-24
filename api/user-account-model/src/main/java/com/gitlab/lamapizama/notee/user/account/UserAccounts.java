package com.gitlab.lamapizama.notee.user.account;


import io.vavr.control.Option;

public interface UserAccounts {

    Option<UserAccount> findBy(UserEmail userEmail);

    UserAccount publish(UserAccountEvent event);

    boolean containsWith(UserEmail userEmail);
}
