package com.gitlab.lamapizama.notee.note.note;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
class NoteEventSerializer {

    private final ObjectMapper objectMapper;

    NoteEventSerializer() {
        this.objectMapper = new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    NoteEventDescriptor serialize(NoteEvent event) {
        try {
            return new NoteEventDescriptor(
                    objectMapper.writeValueAsString(event),
                    event.getWhen(),
                    event.getType(),
                    event.getNoteId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    NoteEvent deserialize(NoteEventDescriptor eventDescriptor) {
        try {
            return objectMapper.readValue(eventDescriptor.body, NoteEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
