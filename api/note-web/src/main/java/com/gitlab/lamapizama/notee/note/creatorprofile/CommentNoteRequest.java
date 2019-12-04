package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class CommentNoteRequest {
    @NotBlank String commentContent;
}
