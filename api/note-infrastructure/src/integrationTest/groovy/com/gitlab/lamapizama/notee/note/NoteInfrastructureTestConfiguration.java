package com.gitlab.lamapizama.notee.note;

import com.gitlab.lamapizama.notee.note.notebook.NotebookConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({NoteConfiguration.class, NotebookConfiguration.class})
public class NoteInfrastructureTestConfiguration { }
