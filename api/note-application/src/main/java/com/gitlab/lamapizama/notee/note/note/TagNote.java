package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

@Value
public class TagNote {
    @NonNull NoteId noteId;
    @NonNull Tag tag;
}
