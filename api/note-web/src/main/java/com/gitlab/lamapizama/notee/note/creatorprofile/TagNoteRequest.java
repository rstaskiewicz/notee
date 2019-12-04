package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class TagNoteRequest {
    @NotEmpty String tagValue;
}
