package com.gitlab.lamapizama.notee.note.creator


import com.gitlab.lamapizama.notee.note.notebook.NotebookName
import com.gitlab.lamapizama.notee.note.notebook.NotebookId
import io.vavr.Tuple
import io.vavr.Tuple2
import spock.lang.Specification

import static CreatorFixture.anyCreatorId
import static CreatorType.Regular
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.anyNotebookId
import static com.gitlab.lamapizama.notee.note.notebook.NotebookFixture.someNotebookName

class CreatorEntityToDomainModelMappingSpec extends Specification {

    CreatorDomainModelMapper domainModelMapper = new CreatorDomainModelMapper(new CreatorFactory())

    CreatorId creatorId = anyCreatorId()
    NotebookId notebookId = anyNotebookId()
    NotebookId anotherNotebookId = anyNotebookId()
    NotebookName notebookName = someNotebookName()
    NotebookName anotherNotebookName = new NotebookName("Another")

    def 'should map creator possessions'() {
        given:
            CreatorEntity entity = regularCreatorEntityWithPossessions()
        when:
            Set<Tuple2<NotebookId, NotebookName>> possessions = domainModelMapper.mapCreatorPossessions(entity)
        then:
            possessions.size() == 2
            possessions.contains(Tuple.of(notebookId, notebookName))
            possessions.contains(Tuple.of(anotherNotebookId, anotherNotebookName))
    }

    CreatorEntity regularCreatorEntityWithPossessions() {
        CreatorEntity entity = new CreatorEntity(creatorId, Regular)
        entity.possessions = [
                new PossessionEntity(notebookId.id, notebookName.name, entity),
                new PossessionEntity(anotherNotebookId.id, anotherNotebookName.name, entity)]
        return entity
    }
}
