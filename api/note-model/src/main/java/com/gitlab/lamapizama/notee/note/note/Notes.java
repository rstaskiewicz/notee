package com.gitlab.lamapizama.notee.note.note;

interface Notes {

    Note getBy(NoteId noteId);

    void save(Note note);

    void publish(NoteEvent event);
}
