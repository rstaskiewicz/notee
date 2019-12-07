package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

@Value
public class RestoreNote {
    @NonNull NoteId noteId;
    @NonNull RestoreEventId revertEventId;
}
