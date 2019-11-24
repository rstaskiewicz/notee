package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.note.creator.CreatorEvent
import com.gitlab.lamapizama.notee.note.creator.CreatorId
import io.vavr.control.Option
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static NotebookFixture.anyNotebookId
import static NotebookFixture.someNotebookName

class CreatorEventsHandlerSpec extends Specification {

    NotebookId notebookId = anyNotebookId()
    NotebookName notebookName = someNotebookName()
    CreatorId creatorId = anyCreatorId()

    Notebooks notebooks = new NotebookInMemoryRepository()
    CreatorEventsHandler handler = new CreatorEventsHandler(notebooks)

    def 'should create new notebook when notebook was created by creator'() {
        when:
            handler.handle(notebookCreated())
            Option<Notebook> notebook = notebooks.findBy(notebookId)
        then:
            notebook.isDefined()
            notebook.get().with {
                assert it.notebookId() == notebookId
                assert it.name() == notebookName
                assert it.owner() == creatorId
            }
    }

    CreatorEvent.NotebookCreated notebookCreated() {
        return CreatorEvent.NotebookCreated.now(notebookId, notebookName, creatorId)
    }
}
