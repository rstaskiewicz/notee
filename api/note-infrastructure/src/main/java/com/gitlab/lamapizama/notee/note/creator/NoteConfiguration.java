package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.events.DomainEventPublisher;
import com.gitlab.lamapizama.notee.commons.events.DomainEvents;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class NoteConfiguration {

    @Bean
    @Primary
    DomainEvents domainEvents(ApplicationEventPublisher applicationEventPublisher) {
        return new DomainEventPublisher(applicationEventPublisher);
    }

    @Bean
    CreatorDomainModelMapper creatorDomainModelMapper() {
        return new CreatorDomainModelMapper(new CreatorFactory());
    }
}
