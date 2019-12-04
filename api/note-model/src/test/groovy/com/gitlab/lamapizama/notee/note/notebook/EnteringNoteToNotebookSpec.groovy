package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.note.creator.Creator
import com.gitlab.lamapizama.notee.note.note.Note
import com.gitlab.lamapizama.notee.note.note.NoteType
import io.vavr.control.Either
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.regularCreator
import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.publicNote
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.notebookFor
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.notebookWith

class EnteringNoteToNotebookSpec extends Specification {

    def 'another note with the same name should not be able to be entered'() {
        given:
            Note note = publicNote()
        and:
            Notebook notebook = notebookWith(note)
        when:
            Either<NotebookEvent.NoteEnteringFailed, NotebookEvent.NoteEntered> entering = notebook
                    .enterNote(note.name(), NoteType.Public, anyCreatorId())
        then:
            entering.isLeft()
            entering.getLeft().reason == 'Cannot enter another note with the same name'
    }

    def 'not notebook owner should not be able to enter note'() {
        given:
            Note note = publicNote()
        and:
            Creator creator = regularCreator()
        and:
            Notebook notebook = notebookFor(regularCreator())
        when:
            Either<NotebookEvent.NoteEnteringFailed, NotebookEvent.NoteEntered> entering = notebook
                    .enterNote(note.name(), NoteType.Public, creator.id())
        then:
            entering.isLeft()
            entering.getLeft().reason == 'Only notebook owner can enter notes'
    }

    def 'a notebook owner should be able to enter note'() {
        given:
            Note note = publicNote()
        and:
            Creator creator = regularCreator()
        and:
            Notebook notebook = notebookFor(creator)
        when:
            Either<NotebookEvent.NoteEnteringFailed, NotebookEvent.NoteEntered> entering = notebook
                    .enterNote(note.name(), NoteType.Public, creator.id())
        then:
            entering.isRight()
            entering.get().with {
                assert it.creatorId == creator.id().getId()
                assert it.noteName == note.name().setNoteName
                assert it.noteType == note.note.type
            }
    }

//    def 'a '
}
