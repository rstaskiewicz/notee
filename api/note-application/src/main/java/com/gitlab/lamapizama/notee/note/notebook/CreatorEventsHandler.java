package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated;
import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookDeleted;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
class CreatorEventsHandler {

    private final Notebooks notebooks;

    @EventListener
    void handle(NotebookCreated event) {
        notebooks.save(new Notebook(
                new NotebookId(event.getNotebookId()),
                new NotebookName(event.getNotebookName()),
                new CreatorId(event.getCreatorId())));
    }

    @EventListener
    void handle(NotebookDeleted event) {
        notebooks.findBy(new NotebookId(event.getNotebookId()))
                .peek(notebooks::remove);
    }
}
