package com.gitlab.lamapizama.notee.note.note;

import lombok.NonNull;
import lombok.Value;

import static com.google.common.base.Preconditions.checkArgument;

@Value
class CommentContent {

    @NonNull String content;

    public CommentContent(@NonNull String content) {
        checkArgument(!content.isEmpty(), "Comment content cannot be empty");
        this.content = content;
    }
}
