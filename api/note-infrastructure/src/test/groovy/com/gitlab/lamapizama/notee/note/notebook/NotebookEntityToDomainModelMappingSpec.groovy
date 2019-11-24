package com.gitlab.lamapizama.notee.note.notebook


import com.gitlab.lamapizama.notee.note.note.NoteId
import com.gitlab.lamapizama.notee.note.note.NoteName
import io.vavr.Tuple
import io.vavr.Tuple2
import spock.lang.Specification

import static com.gitlab.lamapizama.notee.note.creator.CreatorFixture.anyCreatorId
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.anyNoteId
import static com.gitlab.lamapizama.notee.note.note.NoteFixture.anyNoteName
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.anyNotebookId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

class NotebookEntityToDomainModelMappingSpec extends Specification {

    NotebookDomainModelMapper domainModelMapper = new NotebookDomainModelMapper()

    NoteId noteId = anyNoteId()
    NoteName noteName = anyNoteName()
    NoteId anotherNoteId = anyNoteId()
    NoteName anotherNoteName = anyNoteName()


    def 'should map notebook entries'() {
        given:
            NotebookEntity entity = notebookEntityWithEntries()
        when:
            Set<Tuple2<NoteId, NoteName>> possessions = domainModelMapper.mapNotebookEntries(entity)
        then:
            possessions.size() == 2
            possessions.contains(Tuple.of(noteId, noteName))
            possessions.contains(Tuple.of(anotherNoteId, anotherNoteName))
    }

    NotebookEntity notebookEntityWithEntries() {
        NotebookEntity entity = new NotebookEntity(anyNotebookId(), someNotebookName(), anyCreatorId())
        entity.entries = [
                new NotebookEntryEntity(noteId.id, noteName.name, entity),
                new NotebookEntryEntity(anotherNoteId.id, anotherNoteName.name, entity)]
        return entity
    }
}
