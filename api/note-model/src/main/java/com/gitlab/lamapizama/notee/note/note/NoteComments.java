package com.gitlab.lamapizama.notee.note.note;

import io.vavr.collection.List;
import lombok.NonNull;
import lombok.Value;

@Value
class NoteComments {

    @NonNull
    List<Comment> comments;
}
