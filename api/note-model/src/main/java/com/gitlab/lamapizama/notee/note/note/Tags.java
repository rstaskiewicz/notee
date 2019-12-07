package com.gitlab.lamapizama.notee.note.note;

import io.vavr.collection.Set;
import lombok.NonNull;
import lombok.Value;

@Value
class Tags {

    @NonNull Set<Tag> tags;

    public Tags(@NonNull Set<Tag> tags) {
        this.tags = tags;
    }

    Tags add(@NonNull Tag tag) {
        return new Tags(this.tags.add(tag));
    }
}
