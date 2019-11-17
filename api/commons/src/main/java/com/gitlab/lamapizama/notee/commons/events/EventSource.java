package com.gitlab.lamapizama.notee.commons.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSource {

    String OUTPUT = "event-out";

    @Output(OUTPUT)
    MessageChannel output();
}
