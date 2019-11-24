package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(of = "note")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Note {

    @NonNull
    NoteInformation note;

    @NonNull
    NotebookId container;

    public NoteId id() {
        return note.getNoteId();
    }

    public NoteName name() {
        return note.getNoteName();
    }
}
