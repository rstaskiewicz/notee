package com.gitlab.lamapizama.notee.user.account;

import lombok.NonNull;
import lombok.Value;

@Value
public class RawPassword {

    @NonNull
    String password;
}
