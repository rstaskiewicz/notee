package com.gitlab.lamapizama.notee.note.creator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@RequiredArgsConstructor
class NoteConfiguration {

    @Bean CreatorDomainModelMapper creatorDomainModelMapper() {
        return new CreatorDomainModelMapper(new CreatorFactory());
    }
}
