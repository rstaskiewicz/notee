package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
class EditNoteRequest {
    @NotNull FancyNoteContent content;
}
