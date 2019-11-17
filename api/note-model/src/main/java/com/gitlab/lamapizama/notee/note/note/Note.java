package com.gitlab.lamapizama.notee.note.note;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Note {

    @NonNull
    NoteInformation note;

    public NoteId getNoteId() {
        return note.getNoteId();
    }

    boolean isPrivate() {
        return note.isPrivate();
    }
}
