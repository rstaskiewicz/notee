package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import io.vavr.Tuple2;
import io.vavr.collection.HashSet;

import java.util.Set;

public class CreatorFactory {

    Creator create(CreatorId creatorId, CreatorType creatorType, Set<Tuple2<NotebookId, NotebookName>> possessions) {
        return new Creator(
                new CreatorInformation(creatorId, creatorType),
                new CreatorPossessions(possessions.stream()
                .map(tuple -> new Possession(tuple._1, tuple._2))
                .collect(HashSet.collector())));
    }
}
