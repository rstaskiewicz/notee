package com.gitlab.lamapizama.notee.note.note.content;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Value
@Getter
public class NoteBlock {
    @NonNull BlockType type;
    @NonNull NoteData data;
}
