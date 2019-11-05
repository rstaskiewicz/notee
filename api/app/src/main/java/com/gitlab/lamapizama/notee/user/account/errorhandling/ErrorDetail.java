package com.gitlab.lamapizama.notee.user.account.errorhandling;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Value
@Builder
class ErrorDetail {
    String title;
    int status;
    String detail;
    Instant timestamp;
    String exceptionName;
    Map<String, List<String>> errors = new HashMap<>();
}
