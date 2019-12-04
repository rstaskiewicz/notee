package com.gitlab.lamapizama.notee.note.note;


import io.vavr.control.Option;

interface Notes {

    Note getBy(NoteId noteId);

    void publish(NoteEvent event);

    Option<NoteEvent> findEventFor(NoteId noteId, RestoreEventId revertEventId);
}
