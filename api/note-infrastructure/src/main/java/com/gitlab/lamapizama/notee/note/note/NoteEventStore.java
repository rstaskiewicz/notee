package com.gitlab.lamapizama.notee.note.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface NoteEventStore extends JpaRepository<NoteEventStream, Long> {

    Optional<NoteEventStream> findByNoteId(UUID noteId);

    default void saveEvent(UUID noteId, NoteEventDescriptor event) {
        NoteEventStream stream = findByNoteId(noteId)
                .orElseGet(() -> new NoteEventStream(noteId));
        stream.addEvent(event);
        save(stream);
    }

    default List<NoteEventDescriptor> getEventsForNote(UUID noteId) {
        return findByNoteId(noteId)
                .map(NoteEventStream::getEvents)
                .orElseGet(Collections::emptyList);
    }
}
