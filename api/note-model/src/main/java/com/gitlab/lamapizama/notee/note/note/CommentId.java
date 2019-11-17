package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CommentId {
    @NonNull UUID commentId;
}
