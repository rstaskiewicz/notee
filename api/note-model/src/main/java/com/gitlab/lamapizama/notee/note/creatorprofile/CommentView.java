package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
class CommentView {

    @NonNull Integer commentId;
    @NonNull String commentContent;
    @NonNull UUID noteId;
    @NonNull Instant createdAt;
    @NonNull Instant modifiedAt;
}
