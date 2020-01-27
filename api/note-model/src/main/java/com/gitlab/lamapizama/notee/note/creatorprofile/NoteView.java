package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.note.NoteType;
import com.gitlab.lamapizama.notee.note.note.content.FancyNoteContent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
class NoteView {

    @NonNull UUID noteId;
    @NonNull String noteName;
    @NonNull NoteType noteType;
    @NonNull String noteContent;
    @NonNull String createdBy;
    @NonNull String modifiedBy;
    @NonNull Instant createdAt;
    @NonNull Instant modifiedAt;
    @NonNull UUID notebookId;
    @NonNull String notebookName;
    FancyNoteContent content;
    List<CommentView> comments;
    List<TagView> tags;

    public void setNoteType(String noteType) {
        this.noteType = NoteType.valueOf(noteType);
    }
}
