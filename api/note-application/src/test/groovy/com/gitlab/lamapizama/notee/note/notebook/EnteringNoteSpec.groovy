package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade
import com.gitlab.lamapizama.notee.commons.commands.Result
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException
import com.gitlab.lamapizama.notee.note.creator.CreatorId
import com.gitlab.lamapizama.notee.note.note.NoteType
import io.vavr.control.Option
import io.vavr.control.Try
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.anyNoteName
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.publicNote
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.*

class EnteringNoteSpec extends Specification {

    NotebookId notebookId = anyNotebookId()

    Notebooks notebooks = Stub()
    AuthenticationFacade authentication = Stub()

    def 'should fail if notebook does not exists'() {
        given:
           EnteringNote enteringNote = new EnteringNote(authentication, notebooks)
        and:
            unknownNotebook()
        when:
            Try<Result> result = enteringNote.enter(command())
        then:
            result.isFailure()
    }


    def 'should fail if saving notebook fails'() {
        given:
            EnteringNote enteringNote = new EnteringNote(notebooks)
        and:
            persistedRegularCreatorThatFailsOnSaving()
        when:
            Try<Result> result = enteringNote.enter(command())
        then:
            result.isFailure()
    }

    def 'should reject entering a note if one of the domain rules is broken (but should not fail)'() {
        given:
            EnteringNote enteringNote = new EnteringNote(notebooks)
        and:
            persistedNotebookWithNote()
        when:
            Try<Result> result = enteringNote.enter(command())
        then:
            result.isSuccess()
            result.get() == Result.Rejection
    }

    def 'should successfully enter note if notebook exist and note was created'() {
        given:
            EnteringNote enteringNote = new EnteringNote(notebooks)
        and:
            persistedNotebookWithoutNotes()
        when:
            Try<Result> result = enteringNote.enter(command())
        then:
            result.isSuccess()
            result.get() == Result.Success
    }

    void persistedRegularCreatorThatFailsOnSaving() {
        Notebook notebook = notebook()
        notebooks.findBy(notebookId) >> Option.of(notebook)
        notebooks.publish(_ as NotebookEvent) >> { throw new ResourceNotFoundException() }
    }

    void persistedNotebookWithNote() {
        Notebook notebook = notebookWith(publicNote())
        notebooks.findBy(notebookId) >> Option.of(notebook)
        notebooks.publish(_ as NotebookEvent) >> notebook
    }

    void persistedNotebookWithoutNotes() {
        Notebook notebook = notebook()
        notebooks.findBy(notebookId) >> Option.of(notebook)
        notebooks.publish(_ as NotebookEvent) >> notebook
    }

    NotebookId unknownNotebook() {
        return anyNotebookId()
    }

    CreatorId unknownCreator() {
        return anyCreatorId()
    }

    EnterNote command() {
        return new EnterNote(notebookId, anyNoteName(), NoteType.Public)
    }
}
