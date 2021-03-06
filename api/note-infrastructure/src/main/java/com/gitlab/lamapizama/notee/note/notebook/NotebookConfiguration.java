package com.gitlab.lamapizama.notee.note.notebook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class NotebookConfiguration {

    @Bean
    NotebookDomainModelMapper notebookDomainModelMapper() {
        return new NotebookDomainModelMapper(new NotebookFactory());
    }
}
