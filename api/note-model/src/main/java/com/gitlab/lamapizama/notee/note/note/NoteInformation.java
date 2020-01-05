package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import lombok.NonNull;
import lombok.Value;

import static com.gitlab.lamapizama.notee.note.note.NoteType.Private;

@Value
class NoteInformation {
    @NonNull NoteId noteId;
    @NonNull NoteName noteName;
    @NonNull NoteType type;
    @NonNull CreatorId owner;
    @NonNull NotebookId container;

    boolean isPrivate() {
        return type.equals(Private);
    }
}
