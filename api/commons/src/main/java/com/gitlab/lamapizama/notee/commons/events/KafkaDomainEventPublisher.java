package com.gitlab.lamapizama.notee.commons.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

@EnableBinding(EventSource.class)
@RequiredArgsConstructor
public class KafkaDomainEventPublisher implements DomainEvents {

    private final EventSource source;

    @Override
    public void publish(DomainEvent event) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", event.getClass().getSimpleName());
        source.output().send(new GenericMessage<>(event, headers));
    }
}
