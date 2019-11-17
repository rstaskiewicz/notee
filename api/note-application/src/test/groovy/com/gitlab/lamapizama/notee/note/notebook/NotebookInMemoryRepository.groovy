package com.gitlab.lamapizama.notee.note.notebook

import io.vavr.control.Option

class NotebookInMemoryRepository implements Notebooks {

    Map<NotebookId, Notebook> notebooks = new HashMap<>();

    @Override
    Option<Notebook> findBy(NotebookId notebookId) {
        return Option.of(notebooks.get(notebookId))
    }

    @Override
    void save(Notebook notebook) {
        notebooks.put(notebook.notebookId(), notebook)
    }

    @Override
    void remove(Notebook notebook) {
        notebooks.remove(notebook)
    }
}
