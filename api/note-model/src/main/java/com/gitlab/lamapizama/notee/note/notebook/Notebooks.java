package com.gitlab.lamapizama.notee.note.notebook;

import io.vavr.control.Option;

interface Notebooks {

    Option<Notebook> findBy(NotebookId notebookId);

    void save(Notebook notebook);
}
