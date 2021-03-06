package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.note.NoteType;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnterNoteRequest {

    @NotNull
    @Size(min = 4, max = 32)
    String noteName;

    @NotNull
    NoteType noteType;

    @NotNull
    FancyNoteContent content;

    @NotNull
    List<String> tags = new ArrayList<>();
}
