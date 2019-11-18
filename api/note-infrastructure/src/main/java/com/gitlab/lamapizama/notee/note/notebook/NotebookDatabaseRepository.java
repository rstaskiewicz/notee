package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class NotebookDatabaseRepository implements Notebooks {

    private final NotebookDao notebookDao;

    @Override
    public Option<Notebook> findBy(NotebookId notebookId) {
        return findNotebookById(notebookId)
                .map(NotebookEntity::toDomainModel);
    }

    @Override
    public void save(Notebook notebook) {
        NotebookEntity notebookEntity = findNotebookById(notebook.notebookId())
                .map(entity -> update(entity, notebook))
                .getOrElse(() -> create(notebook));
        notebookDao.save(notebookEntity);
    }

    private Option<NotebookEntity> findNotebookById(NotebookId notebookId) {
        return Option.of(notebookDao.findByNotebookId(notebookId.getNotebookId()));
    }

    private NotebookEntity update(NotebookEntity entity, Notebook notebook) {
        entity.notebookId = notebook.notebookId().getNotebookId();
        entity.notebookName = notebook.name().getName();
        entity.creatorId = notebook.owner().getEmail();
        return entity;
    }
    private NotebookEntity create(Notebook notebook) {
        return update(new NotebookEntity(), notebook);
    }
}
