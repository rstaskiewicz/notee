package com.gitlab.lamapizama.notee.note.creator


import com.gitlab.lamapizama.notee.note.notebook.NotebookId
import com.gitlab.lamapizama.notee.note.notebook.NotebookName
import io.vavr.control.Option
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.CreatorCreated
import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated
import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.regularCreator
import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.someCreatorId
import static com.gitlab.lamapizama.notee.note.creator.CreatorType.Regular
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.anyNotebookId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

@Transactional
@SpringBootTest
class CreatorDatabaseRepositoryTest extends Specification {

    CreatorId creatorId = someCreatorId()
    NotebookId notebookId = anyNotebookId()
    NotebookName notebookName = someNotebookName()

    @Autowired
    Creators creators

    def 'persistence in database should work'() {
        when:
            creators.publish(CreatorCreated.now(creatorId, Regular))
        then:
            creatorShouldBeFoundInDatabaseWithoutNotebooksInPossession(creatorId)
        when:
            creators.publish(NotebookCreated.now(notebookId, notebookName, creatorId))
        then:
            creatorShouldBeFoundInDatabaseWithOneNotebookInPossession(creatorId)
    }

    void creatorShouldBeFoundInDatabaseWithoutNotebooksInPossession(CreatorId creatorId) {
        Creator creator = loadPersistedCreator(creatorId)
        assert creator.numberOfPossessions() == 0
        assert creator == regularCreator(creatorId)
    }

    void creatorShouldBeFoundInDatabaseWithOneNotebookInPossession(CreatorId creatorId) {
        Creator creator = loadPersistedCreator(creatorId)
        assert creator.numberOfPossessions() == 1
        assert creator == regularCreator(creatorId)
    }

    Creator loadPersistedCreator(CreatorId creatorId) {
        Option<Creator> loaded = creators.findBy(creatorId)
        return loaded.getOrElseThrow({ new IllegalStateException("should have been persisted") })
    }
}
