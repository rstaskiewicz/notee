package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Slf4j
@Component
public class ContentSerializer {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    public FancyNoteContent deserialize(String noteContent) {
        try {
            return objectMapper.readValue(noteContent, FancyNoteContent.class);
        } catch (JsonProcessingException e) {
            log.error("Deserialization fails");
        }
        return null;
    }
}
