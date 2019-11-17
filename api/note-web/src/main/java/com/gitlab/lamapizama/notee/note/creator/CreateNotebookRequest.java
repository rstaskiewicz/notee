package com.gitlab.lamapizama.notee.note.creator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateNotebookRequest {

    @NotNull
    @Size(min = 4, max = 32)
    String name;
}
