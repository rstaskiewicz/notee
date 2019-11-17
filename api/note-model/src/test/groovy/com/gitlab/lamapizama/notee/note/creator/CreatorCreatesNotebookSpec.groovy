package com.gitlab.lamapizama.notee.note.creator

import com.gitlab.lamapizama.notee.note.notebook.Notebook
import com.gitlab.lamapizama.notee.note.notebook.NotebookName
import io.vavr.control.Either
import spock.lang.Specification

import static CreatorFixture.regularCreator
import static CreatorFixture.regularCreatorWith
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.notebook
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

class CreatorCreatesNotebookSpec extends Specification {

    def 'creator cannot create a notebook with name that one of his notebooks already has'() {
        given:
            Notebook notebook = notebook()
        and:
            Creator creator = regularCreatorWith(notebook)
        when:
            Either<CreatorEvent.NotebookCreationFailed, CreatorEvent.NotebookCreated> creation = creator.createNotebook(notebook.name())
        then:
            creation.isLeft()
            creation.getLeft().reason == 'Creator cannot create another notebook with the same name'
    }

    def 'creator should be able to create a notebook'() {
        given:
            Creator creator = regularCreator()
        and:
            NotebookName notebookName = someNotebookName()
        when:
            Either<CreatorEvent.NotebookCreationFailed, CreatorEvent.NotebookCreated> creation = creator.createNotebook(notebookName)
        then:
            creation.isRight()
            creation.get().with {
                assert it.notebookName == notebookName.name
                assert it.creatorId == creator.creatorId().email
            }
    }
}
