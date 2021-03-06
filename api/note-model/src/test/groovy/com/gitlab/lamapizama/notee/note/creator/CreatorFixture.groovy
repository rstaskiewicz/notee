package com.gitlab.lamapizama.notee.note.creator

import com.gitlab.lamapizama.notee.note.notebook.Notebook
import io.vavr.collection.HashSet

import static CreatorType.Regular

class CreatorFixture {

    static final String CREATOR_EMAIL_SUFFIX = '@email.com'

    static CreatorId anyCreatorId() {
        return new CreatorId(UUID.randomUUID().toString() + CREATOR_EMAIL_SUFFIX);
    }

    static Creator regularCreator() {
        return regularCreator(anyCreatorId());
    }

    static Creator regularCreator(CreatorId creatorId) {
        return new Creator(
                creatorInformation(creatorId, Regular),
                noPossessions())
    }

    static Creator regularCreatorWith(Notebook notebook) {
        return new Creator(
                creatorInformation(anyCreatorId(), Regular),
                possessions(notebook))
    }

    private static CreatorInformation creatorInformation(CreatorId creatorId, CreatorType type) {
        return new CreatorInformation(creatorId, type)
    }

    private static CreatorPossessions noPossessions() {
        return new CreatorPossessions(HashSet.empty())
    }

    private static CreatorPossessions possessions(Notebook notebook) {
        return new CreatorPossessions(HashSet.of(new Possession(notebook.notebookId(), notebook.name())))
    }
}
