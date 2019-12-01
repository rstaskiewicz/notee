package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

@Value
class NoteContent {
    @NonNull String content;

    static NoteContent empty() {
        return new NoteContent("");
    }
}
