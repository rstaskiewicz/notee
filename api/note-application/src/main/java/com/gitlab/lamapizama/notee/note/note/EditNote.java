package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

@Value
public class EditNote {
    @NonNull NoteId noteId;
    @NonNull NoteContent noteContent;
}
