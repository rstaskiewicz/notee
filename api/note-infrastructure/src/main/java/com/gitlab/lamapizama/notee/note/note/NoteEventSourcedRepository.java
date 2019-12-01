package com.gitlab.lamapizama.notee.note.note;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gitlab.lamapizama.notee.note.note.Note.rebuild;

@Repository
@RequiredArgsConstructor
class NoteEventSourcedRepository implements Notes {

    private final NoteEventStore eventStore;
    private final NoteEventSerializer eventSerializer;

    @Override
    public Note getBy(NoteId noteId) {
        return rebuild(noteId, getRelatedEvents(noteId));
    }

    @Override
    public void save(Note note) {
    }

    @Override
    public void publish(NoteEvent event) {
        eventStore.saveEvent(event.getNoteId(), eventSerializer.serialize(event));
    }

    private List<NoteEvent> getRelatedEvents(NoteId noteId) {
        return eventStore.getEventsForNote(noteId.getId()).stream()
                .map(eventSerializer::deserialize)
                .collect(List.collector());
    }
}
