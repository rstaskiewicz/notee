package com.gitlab.lamapizama.notee.commons.policies;

import lombok.NonNull;
import lombok.Value;

@Value
public class Rejection {

    @NonNull
    Reason reason;

    public static Rejection withReason(String reason) {
        return new Rejection(new Reason(reason));
    }

    public String getReason() {
        return reason.getReason();
    }

    @Value
    static class Reason {
        @NonNull String reason;
    }
}
