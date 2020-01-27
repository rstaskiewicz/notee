package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.note.NoteName;
import com.gitlab.lamapizama.notee.note.note.NoteType;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import lombok.NonNull;
import lombok.Value;

@Value
public class EnterNote {
    @NonNull NotebookId notebookId;
    @NonNull NoteName noteName;
    @NonNull NoteType noteType;
    @NonNull FancyNoteContent content;
}
