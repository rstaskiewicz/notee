package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import io.vavr.collection.HashSet;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(of = "notebook")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Notebook {

    @NonNull
    NotebookInformation notebook;

    @NonNull
    CreatorId owner;

    @NonNull
    NotebookEntries notebookEntries;

    Notebook(NotebookId notebookId, NotebookName notebookName, CreatorId creatorId) {
        this(new NotebookInformation(notebookId, notebookName), creatorId, new NotebookEntries(HashSet.empty()));
    }

    public NotebookId notebookId() {
        return notebook.getNotebookId();
    }

    public NotebookName name() {
        return notebook.getName();
    }

    CreatorId owner() {
        return owner;
    }
}
