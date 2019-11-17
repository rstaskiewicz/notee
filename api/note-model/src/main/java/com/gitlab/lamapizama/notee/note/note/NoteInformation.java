package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

import static com.gitlab.lamapizama.notee.note.note.NoteType.Private;

@Value
class NoteInformation {
    @NonNull NoteId noteId;
    @NonNull NoteType type;

    boolean isPrivate() {
        return type.equals(Private);
    }
}
