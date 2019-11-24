package com.gitlab.lamapizama.notee.note.note

class NoteFixture {

    static final String NOTE_NAME_SUFFIX = ' note'

    static NoteId anyNoteId() {
        return new NoteId(UUID.randomUUID())
    }

    static NoteName anyNoteName() {
        return new NoteName(UUID.randomUUID().toString() + NOTE_NAME_SUFFIX);
    }

    static Note publicNote() {
        return new Note(new NoteInformation(anyNoteId(), anyNoteName(), NoteType.Public))
    }
}
