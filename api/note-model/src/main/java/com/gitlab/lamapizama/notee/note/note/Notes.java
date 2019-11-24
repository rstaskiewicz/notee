package com.gitlab.lamapizama.notee.note.note;

import io.vavr.control.Option;

interface Notes {

    Option<Note> findBy(NoteId noteId);

    void save(Note note);
}
