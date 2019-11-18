package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.note.creator.CreatorId
import io.vavr.collection.HashSet

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.someCreatorId

class NotebookFixture {

    static final String NOTEBOOK_NAME = 'Notebook'

    static Notebook notebook() {
        return new Notebook(
                new NotebookInformation(anyNotebookId(), someNotebookName()),
                someCreatorId(),
                noEntries())
    }

    static Notebook notebook(CreatorId creatorId, NotebookName notebookName) {
        return new Notebook(
                new NotebookInformation(anyNotebookId(), notebookName),
                creatorId,
                noEntries())
    }

    static Notebook notebook(CreatorId creatorId, NotebookId notebookId, NotebookName notebookName) {
        return new Notebook(
                new NotebookInformation(notebookId, notebookName),
                creatorId,
                noEntries())
    }

    static NotebookId anyNotebookId() {
        return new NotebookId(UUID.randomUUID())
    }

    static NotebookName someNotebookName() {
        return new NotebookName(NOTEBOOK_NAME)
    }

    static NotebookEntries noEntries() {
        return new NotebookEntries(HashSet.empty())
    }
}

