package com.gitlab.lamapizama.notee.commons.events;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {

    UUID getEventId();

    Instant getTimestamp();
}
