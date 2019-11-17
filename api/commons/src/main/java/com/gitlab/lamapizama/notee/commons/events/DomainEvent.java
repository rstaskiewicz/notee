package com.gitlab.lamapizama.notee.commons.events;

import java.time.Instant;

public interface DomainEvent<T> {

    T getAggregateId();

    Instant getWhen();
}
