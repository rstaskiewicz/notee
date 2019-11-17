package com.gitlab.lamapizama.notee.note.creator

import com.gitlab.lamapizama.notee.note.notebook.NotebookId
import com.gitlab.lamapizama.notee.note.notebook.NotebookName
import spock.lang.Specification

import static CreatorFixture.someCreatorId
import static CreatorType.Regular
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.anyNotebookId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

class CreatingDataModelFromCreatorEventsTest extends Specification {

    CreatorId creatorId = someCreatorId()
    NotebookId notebookId = anyNotebookId()
    NotebookName notebookName = someNotebookName()

    def 'should add notebook to creator possessions'() {
        given:
            CreatorEntity entity = regularCreatorEntity()
        when:
            entity.handle(notebookCreated())
        then:
            entity.possessions.size() == 1
            entity.possessions.contains(new PossessionEntity(notebookId.notebookId, notebookName.name, entity))
    }

    CreatorEntity regularCreatorEntity() {
        return new CreatorEntity(creatorId, Regular)
    }

    CreatorEvent.NotebookCreated notebookCreated() {
        return CreatorEvent.NotebookCreated.now(
                notebookId,
                notebookName,
                creatorId)
    }
}
