package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import io.vavr.collection.Set;
import lombok.Value;

@Value
class CreatorPossessions {

    Set<Possession> possessions;

    boolean withId(NotebookId id) {
        return possessions.find(possession -> possession.getNotebookId().equals(id)).isDefined();
    }

    boolean withName(NotebookName name) {
        return possessions.find(possession -> possession.getName().equals(name)).isDefined();
    }

    int count() {
        return possessions.size();
    }
}
