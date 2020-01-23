package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import lombok.NonNull;
import lombok.Value;

@Value
public class EditNote {
    @NonNull NoteId noteId;
    @NonNull FancyNoteContent noteContent;
}
