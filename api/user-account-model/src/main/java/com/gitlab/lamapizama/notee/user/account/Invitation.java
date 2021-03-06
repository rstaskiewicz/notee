package com.gitlab.lamapizama.notee.user.account;

import lombok.NonNull;
import lombok.Value;

@Value
class Invitation {
    @NonNull UserEmail userEmail;
    @NonNull Boolean accepted;
}
