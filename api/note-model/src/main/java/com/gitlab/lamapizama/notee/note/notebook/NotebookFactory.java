package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import io.vavr.Tuple2;
import io.vavr.collection.HashSet;

import java.util.Collections;
import java.util.Set;


public class NotebookFactory {

    Notebook create(NotebookId notebookId, NotebookName notebookName, CreatorId ownerId) {
        return create(notebookId, notebookName, ownerId, Collections.emptySet());
    }

    Notebook create(NotebookId notebookId, NotebookName notebookName, CreatorId ownerId, Set<Tuple2<NoteId, NoteName>> entries) {
        return new Notebook(
                new NotebookInformation(notebookId, notebookName),
                NoteEnteringPolicy.allCurrentPolicies(),
                NoteDeletingPolicy.allCurrentPolicies(),
                ownerId,
                new NotebookEntries(entries.stream()
                        .map(tuple -> new NotebookEntry(tuple._1, tuple._2))
                        .collect(HashSet.collector())));
    }
}
