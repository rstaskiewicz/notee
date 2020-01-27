package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCreated;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteEntered;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class NotebookEventsHandler {

    private final Notes notes;

    @EventListener
    void handle(NoteEntered event) {
        log.info("Handled event: " + event);
        notes.publish(NoteCreated.now(
                new NoteId(event.getNoteId()),
                new NoteName(event.getNoteName()),
                event.getNoteType(),
                new CreatorId(event.getCreatorId()),
                new NotebookId(event.getNotebookId()),
                event.getContent()));
    }
}
