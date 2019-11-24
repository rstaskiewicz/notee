package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.events.DomainEvents;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.CreatorCreated;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

@Repository
@RequiredArgsConstructor
class CreatorDatabaseRepository implements Creators {

    private final CreatorDao creatorDao;
    private final DomainEvents events;
    private final CreatorDomainModelMapper domainModelMapper;

    @Override
    public Option<Creator> findBy(CreatorId creatorId) {
        return Option.of(creatorDao.findByCreatorId(creatorId.getId()))
                .map(domainModelMapper::map);
    }

    @Override
    public Creator publish(CreatorEvent event) {
        Creator result = Match(event).of(
                Case($(instanceOf(CreatorCreated.class)), this::createNewCreator),
                Case($(), this::handleNextEvent));
        events.publish(event);
        return result;
    }

    private Creator createNewCreator(CreatorCreated event) {
        CreatorEntity entity = creatorDao.save(new CreatorEntity(event.creatorId(), event.getCreatorType()));
        return domainModelMapper.map(entity);
    }

    private Creator handleNextEvent(CreatorEvent event) {
        CreatorEntity entity = creatorDao.findByCreatorId(event.getCreatorId());
        entity = entity.handle(event);
        entity = creatorDao.save(entity);
        return domainModelMapper.map(entity);
    }
}

@RequiredArgsConstructor
class CreatorDomainModelMapper {

    private final CreatorFactory creatorFactory;

    Creator map(CreatorEntity entity) {
        return creatorFactory.create(
                new CreatorId(entity.creatorId),
                entity.creatorType,
                mapCreatorPossessions(entity));
    }

    Set<Tuple2<NotebookId, NotebookName>> mapCreatorPossessions(CreatorEntity entity) {
        return entity.possessions.stream()
                .map(possessionEntity -> Tuple.of(
                        new NotebookId(possessionEntity.possessionId), new NotebookName(possessionEntity.notebookName)))
                .collect(Collectors.toSet());
    }
}
