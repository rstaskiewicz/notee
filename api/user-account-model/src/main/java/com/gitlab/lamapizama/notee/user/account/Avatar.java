package com.gitlab.lamapizama.notee.user.account;

import lombok.NonNull;
import lombok.Value;

@Value
class Avatar {
    @NonNull String imageUrl;
}
