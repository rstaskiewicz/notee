package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

@Value
public class CommentNote {
    @NonNull NoteId noteId;
    @NonNull CommentContent commentContent;
}
