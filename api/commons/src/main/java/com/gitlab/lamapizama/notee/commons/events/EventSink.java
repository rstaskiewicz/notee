package com.gitlab.lamapizama.notee.commons.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventSink {

    String INPUT = "event-in";

    @Input(INPUT)
    SubscribableChannel input();
}
