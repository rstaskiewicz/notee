package com.gitlab.lamapizama.notee.note.note;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import lombok.NonNull;
import lombok.Value;

@Value
class Tags {

    @NonNull Set<Tag> tags;

    public Tags(@NonNull Set<Tag> tags) {
        this.tags = tags;
    }

    public Tags(@NonNull java.util.Set<String> tags) {
        this.tags = tags.stream()
                .map(Tag::new)
                .collect(HashSet.collector());
    }

    Tags add(@NonNull Tags tags) {
        return new Tags(this.tags.addAll(tags.tags));
    }
}
