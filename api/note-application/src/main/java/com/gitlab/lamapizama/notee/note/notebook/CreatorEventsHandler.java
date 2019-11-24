package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CreatorEventsHandler {

    private final Notebooks notebooks;
    private final NotebookFactory notebookFactory = new NotebookFactory();

    @EventListener
    void handle(NotebookCreated event) {
        notebooks.save(notebookFactory.create(
                new NotebookId(event.getNotebookId()),
                new NotebookName(event.getNotebookName()),
                new CreatorId(event.getCreatorId())));
    }
}
