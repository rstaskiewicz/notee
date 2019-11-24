package com.gitlab.lamapizama.notee.note.notebook

import com.gitlab.lamapizama.notee.note.creator.CreatorId
import com.gitlab.lamapizama.notee.note.note.NoteId
import com.gitlab.lamapizama.notee.note.note.NoteName
import com.gitlab.lamapizama.notee.note.note.NoteType
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.anyNoteId
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.anyNoteName
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.anyNotebookId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

class CreatingDataModelFromNotebookEventsSpec extends Specification {

    NotebookId notebookId = anyNotebookId()
    CreatorId creatorId = anyCreatorId()
    NoteId noteId = anyNoteId()
    NoteName noteName = anyNoteName()

    def 'should enter the note to the notebook'() {
        given:
            NotebookEntity entity = noteBookEntity()
        when:
            entity.handle(noteEntered())
        then:
            entity.entries.size() == 1
            entity.entries.contains(new NotebookEntryEntity(noteId.id, entity))
    }

    NotebookEntity noteBookEntity() {
        return new NotebookEntity(notebookId, someNotebookName(), creatorId)
    }

    NotebookEvent.NoteEntered noteEntered() {
        return NotebookEvent.NoteEntered.now(noteId, noteName, NoteType.Public, notebookId, creatorId)
    }
}
