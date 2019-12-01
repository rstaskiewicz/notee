package com.gitlab.lamapizama.notee.note.note;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class EditNoteRequest {
    @NotBlank String noteContent;
}
