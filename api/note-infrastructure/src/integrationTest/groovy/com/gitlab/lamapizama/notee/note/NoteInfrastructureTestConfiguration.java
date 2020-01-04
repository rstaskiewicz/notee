package com.gitlab.lamapizama.notee.note;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.authentication.UserDetails;
import com.gitlab.lamapizama.notee.note.notebook.NotebookConfiguration;
import io.vavr.control.Option;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({NoteConfiguration.class, NotebookConfiguration.class})
public class NoteInfrastructureTestConfiguration {

    @Bean
    AuthenticationFacade authenticationFacade() {
        return () -> Option.of(new UserDetails("integration@test.com"));
    }
}
