package com.gitlab.lamapizama.notee.note.note;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class Comment {

    @NonNull
    CommentId commentId;

    @NonNull
    CommentContent content;
}
