package com.gitlab.lamapizama.notee.note.creator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CreatorConfiguration {

    @Bean
    CreatorDomainModelMapper creatorDomainModelMapper() {
        return new CreatorDomainModelMapper(new CreatorFactory());
    }
}
