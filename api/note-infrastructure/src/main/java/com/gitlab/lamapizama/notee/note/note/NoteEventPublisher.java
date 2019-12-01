package com.gitlab.lamapizama.notee.note.note;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class NoteEventPublisher {

    private final NotePendingEventFetcher eventFetcher;
    private final NoteEventSerializer serializer;
    private final ApplicationEventPublisher events;

    @Scheduled(fixedRate = 2000)
    void publishPendingEvents() {
        eventFetcher.listPending().forEach(this::publish);
    }

    private void publish(NoteEventDescriptor event) {
        log.info("About to publish event: " + event.body);
        events.publishEvent(serializer.deserialize(event));
        eventFetcher.save(event.markAsSent());
        log.info("Event published: " + event.body);
    }
}
