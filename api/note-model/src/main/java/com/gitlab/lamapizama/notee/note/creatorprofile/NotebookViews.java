package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface NotebookViews {

    Option<NotebookView> findBy(NotebookId notebookId);

    List<NoteSimpleView> fetchNotesFor(NotebookId notebookId);
}
