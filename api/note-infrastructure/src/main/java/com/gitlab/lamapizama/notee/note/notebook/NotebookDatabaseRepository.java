package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.commons.events.DomainEvents;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class NotebookDatabaseRepository implements Notebooks {

    private final NotebookDao notebookDao;
    private final DomainEvents events;
    private final NotebookDomainModelMapper domainModelMapper;

    @Override
    public Option<Notebook> findBy(NotebookId notebookId) {
        return findNotebookById(notebookId)
                .map(domainModelMapper::map);
    }

    @Override
    public void save(Notebook notebook) {
        NotebookEntity notebookEntity = findNotebookById(notebook.notebookId())
                .map(entity -> update(entity, notebook))
                .getOrElse(() -> create(notebook));
        notebookDao.save(notebookEntity);
    }

    @Override
    public Notebook publish(NotebookEvent event) {
        Notebook result = handleNextEvent(event);
        events.publish(event);
        return result;
    }

    private Option<NotebookEntity> findNotebookById(NotebookId notebookId) {
        return Option.of(notebookDao.findByNotebookId(notebookId.getId()));
    }

    private NotebookEntity update(NotebookEntity entity, Notebook notebook) {
        entity.notebookId = notebook.notebookId().getId();
        entity.notebookName = notebook.name().getName();
        entity.creatorId = notebook.owner().getId();
        return entity;
    }
    private NotebookEntity create(Notebook notebook) {
        return update(new NotebookEntity(notebook.notebookId(), notebook.name(), notebook.owner()), notebook);
    }

    private Notebook handleNextEvent(NotebookEvent event) {
        NotebookEntity entity = notebookDao.findByNotebookId(event.getNotebookId());
        entity = entity.handle(event);
        entity = notebookDao.save(entity);
        return domainModelMapper.map(entity);
    }
}

@RequiredArgsConstructor
class NotebookDomainModelMapper {

    private final NotebookFactory notebookFactory;

    Notebook map(NotebookEntity entity) {
        return notebookFactory.create(
                new NotebookId(entity.notebookId),
                new NotebookName(entity.notebookName),
                new CreatorId(entity.creatorId),
                mapNotebookEntries(entity));
    }

    private Set<Tuple2<NoteId, NoteName>> mapNotebookEntries(NotebookEntity entity) {
        return entity.entries.stream()
                .map(entryEntity -> Tuple.of(
                        new NoteId(entryEntity.entryId), new NoteName(entryEntity.entryName)))
                .collect(Collectors.toSet());
    }
}
