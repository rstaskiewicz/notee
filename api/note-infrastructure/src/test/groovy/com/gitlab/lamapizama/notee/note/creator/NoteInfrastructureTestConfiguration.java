package com.gitlab.lamapizama.notee.note.creator;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({NoteConfiguration.class})
class NoteInfrastructureTestConfiguration { }
