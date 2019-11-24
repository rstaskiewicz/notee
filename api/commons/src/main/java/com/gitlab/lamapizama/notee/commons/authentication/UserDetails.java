package com.gitlab.lamapizama.notee.commons.authentication;

import lombok.NonNull;
import lombok.Value;

@Value
public class UserDetails {
    @NonNull String userId;
}
