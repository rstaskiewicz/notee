package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.note.creator.Creator
import com.gitlab.lamapizama.notee.note.creator.CreatorId
import com.gitlab.lamapizama.notee.note.note.Note
import io.vavr.collection.HashSet

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId

class NotebookFixture {

    static final String NOTEBOOK_NAME = 'Notebook'

    static NotebookId anyNotebookId() {
        return new NotebookId(UUID.randomUUID())
    }

    static NotebookName someNotebookName() {
        return new NotebookName(NOTEBOOK_NAME)
    }

    static Notebook notebook() {
        return new Notebook(
                new NotebookInformation(anyNotebookId(), someNotebookName()),
                NoteEnteringPolicy.allCurrentPolicies(),
                NoteDeletingPolicy.allCurrentPolicies(),
                anyCreatorId(),
                noEntries())
    }

    static Notebook notebook(CreatorId creatorId, NotebookName notebookName) {
        return new Notebook(
                new NotebookInformation(anyNotebookId(), notebookName),
                NoteEnteringPolicy.allCurrentPolicies(),
                NoteDeletingPolicy.allCurrentPolicies(),
                creatorId,
                noEntries())
    }

    static Notebook notebook(CreatorId creatorId, NotebookId notebookId, NotebookName notebookName) {
        return new Notebook(
                new NotebookInformation(notebookId, notebookName),
                NoteEnteringPolicy.allCurrentPolicies(),
                NoteDeletingPolicy.allCurrentPolicies(),
                creatorId,
                noEntries())
    }

    static Notebook notebookWith(Note note) {
        return new Notebook(
                new NotebookInformation(anyNotebookId(), someNotebookName()),
                NoteEnteringPolicy.allCurrentPolicies(),
                NoteDeletingPolicy.allCurrentPolicies(),
                anyCreatorId(),
                entries(note))
    }

    static Notebook notebookFor(Creator creator) {
        return new Notebook(
                new NotebookInformation(anyNotebookId(), someNotebookName()),
                NoteEnteringPolicy.allCurrentPolicies(),
                NoteDeletingPolicy.allCurrentPolicies(),
                creator.id(),
                noEntries())
    }

    static NotebookEntries noEntries() {
        return new NotebookEntries(HashSet.empty())
    }

    private static NotebookEntries entries(Note note) {
        return new NotebookEntries(HashSet.of(new NotebookEntry(note.id(), note.name())))
    }
}

