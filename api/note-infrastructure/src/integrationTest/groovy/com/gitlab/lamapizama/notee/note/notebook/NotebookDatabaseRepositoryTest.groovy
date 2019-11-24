package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.note.creator.CreatorId
import io.vavr.control.Option
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.*

@Transactional
@DataJpaTest
class NotebookDatabaseRepositoryTest extends Specification {

    CreatorId creatorId = anyCreatorId()
    NotebookId notebookId = anyNotebookId()
    NotebookName notebookName = someNotebookName()

    @Autowired
    Notebooks notebooks

    def 'persistence in database should work'() {
        when:
            notebooks.save(notebook(creatorId, notebookId, notebookName))
        then:
            notebookShouldBeFoundInDatabaseWithoutPostedNotes(notebookId)
    }

    void notebookShouldBeFoundInDatabaseWithoutPostedNotes(NotebookId notebookId) {
        Notebook notebook = loadPersistedNotebook(notebookId)
        assert notebook == NotebookFixture.notebook(creatorId, notebookId, notebookName)
    }

    Notebook loadPersistedNotebook(NotebookId notebookId) {
        Option<Notebook> loaded = notebooks.findBy(notebookId)
        return loaded.getOrElseThrow({ new IllegalStateException("should have been persisted") })
    }
}
