package com.gitlab.lamapizama.notee.note.note;

import io.vavr.collection.Set;
import lombok.NonNull;
import lombok.Value;

@Value
class NoteComments {

    @NonNull
    Set<Comment> comments;

    Integer generateNewId() {
        return comments.size() + 1;
    }

    NoteComments addComment(@NonNull Comment comment) {
        return new NoteComments(comments.add(comment));
    }
}
