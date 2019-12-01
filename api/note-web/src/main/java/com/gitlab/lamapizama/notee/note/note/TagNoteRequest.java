package com.gitlab.lamapizama.notee.note.note;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class TagNoteRequest {
    @NotEmpty Set<String> tags;
}
