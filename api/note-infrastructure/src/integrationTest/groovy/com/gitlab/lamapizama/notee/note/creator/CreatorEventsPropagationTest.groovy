package com.gitlab.lamapizama.notee.note.creator

import com.gitlab.lamapizama.notee.note.notebook.NotebookDao
import com.gitlab.lamapizama.notee.note.notebook.NotebookName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

@Transactional
@DataJpaTest
class CreatorEventsPropagationTest extends Specification {

    CreatorId creatorId = anyCreatorId()
    NotebookName notebookName = someNotebookName()

    @Autowired
    CreatingNotebook creatingNotebook

    @Autowired
    CreatorDao creatorDao

    @Autowired
    NotebookDao notebookDao

    def "should create notebook entity when notebook entity is created"() {
        given:
            creatorPersistedInDatabase()
        when:
            creatingNotebook.create(new CreateNotebook(creatorId, notebookName))
        then:
            notebookEntityShouldBeFoundInDatabase()
    }

    void creatorPersistedInDatabase() {
        creatorDao.save(new CreatorEntity(creatorId, CreatorType.Regular))
    }

    void notebookEntityShouldBeFoundInDatabase() {
        notebookDao.findAll().size() == 1
    }
}
