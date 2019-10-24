package com.gitlab.lamapizama.notee.user.account;

import lombok.NonNull;
import lombok.Value;

@Value
class UserInformation {

    @NonNull UserEmail email;
    @NonNull Username username;
    @NonNull EncodedPassword password;
    boolean verified;

    public UserInformation(@NonNull UserEmail userEmail,
                           @NonNull Username username,
                           @NonNull EncodedPassword password,
                           boolean verified) {
        this.email = userEmail;
        this.username = username;
        this.password = password;
        this.verified = verified;
    }
}
