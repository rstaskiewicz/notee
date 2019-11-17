package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.events.EventSink;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.time.Instant;

import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.*;

@Slf4j
@EnableBinding(EventSink.class)
@RequiredArgsConstructor
class CreatorEventConsumer {

    private final Creators creators;

    @StreamListener(target = EventSink.INPUT, condition = "headers['type'] == 'UserAccountConfirmed'")
    public void handle(@Payload UserAccountConfirmed event) {
        log.info("Received: " + event);
        creators.publish(CreatorCreated.now(new CreatorId(event.userEmail), CreatorType.Regular));
    }
}

@Data
@NoArgsConstructor
class UserAccountConfirmed {
    Instant when;
    String userEmail;
}
