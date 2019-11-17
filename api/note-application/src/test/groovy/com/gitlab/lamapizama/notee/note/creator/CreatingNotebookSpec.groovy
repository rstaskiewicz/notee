package com.gitlab.lamapizama.notee.note.creator


import com.gitlab.lamapizama.notee.commons.commands.Result
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException
import io.vavr.control.Option
import io.vavr.control.Try
import spock.lang.Specification

import static CreatorFixture.*
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.notebook
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

class CreatingNotebookSpec extends Specification {

    CreatorId creatorId = someCreatorId()

    Creators creators = Stub()

    def 'should fail if creator does not exists'() {
        given:
            CreatingNotebook creatingNotebook = new CreatingNotebook(creators)
        and:
            unknownCreator()
        when:
            Try<Result> result = creatingNotebook.create(command())
        then:
            result.isFailure()
    }

    def 'should fail if saving creator fails'() {
        given:
            CreatingNotebook creatingNotebook = new CreatingNotebook(creators)
        and:
            persistedRegularCreatorThatFailsOnSaving()
        when:
            Try<Result> result = creatingNotebook.create(command())
        then:
            result.isFailure()
    }

    def 'should reject creating a notebook if one of the domain rules is broken (but should not fail)'() {
        given:
            CreatingNotebook creatingNotebook = new CreatingNotebook(creators)
        and:
            persistedRegularCreatorWithNotebook()
        when:
            Try<Result> result = creatingNotebook.create(command())
        then:
            result.isSuccess()
            result.get() == Result.Rejection
    }

    def 'should successfully create notebook if creator exist and notebook was created'() {
        given:
            CreatingNotebook creatingNotebook = new CreatingNotebook(creators)
        and:
            persistedRegularCreatorWithoutNotebooks()
        when:
            Try<Result> result = creatingNotebook.create(command())
        then:
            result.isSuccess()
            result.get() == Result.Success
    }

    CreatorId unknownCreator() {
        return someCreatorId()
    }

    CreatorId persistedRegularCreatorThatFailsOnSaving() {
        Creator creator = regularCreator()
        creators.findBy(creatorId) >> Option.of(creator)
        creators.publish(_ as CreatorEvent) >> { throw new ResourceNotFoundException() }
        return creatorId
    }

    CreatorId persistedRegularCreatorWithNotebook() {
        Creator creator = regularCreatorWith(notebook())
        creators.findBy(creatorId) >> Option.of(creator)
        creators.publish(_ as CreatorEvent) >> creator
        return creatorId
    }

    CreatorId persistedRegularCreatorWithoutNotebooks() {
        Creator creator = regularCreator()
        creators.findBy(creatorId) >> Option.of(creator)
        creators.publish(_ as CreatorEvent) >> creator
        return creatorId
    }

    CreateNotebook command() {
        return new CreateNotebook(creatorId, someNotebookName())
    }
}
