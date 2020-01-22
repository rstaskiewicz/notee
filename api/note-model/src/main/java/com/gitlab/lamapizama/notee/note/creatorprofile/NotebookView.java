package com.gitlab.lamapizama.notee.note.creatorprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
class NotebookView {

    @NonNull UUID notebookId;
    @NonNull String notebookName;
    @NonNull @With List<NoteSimpleView> notes = new ArrayList<>();
}
