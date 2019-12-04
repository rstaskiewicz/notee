package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static io.vavr.collection.List.ofAll;
import static io.vavr.control.Option.none;
import static io.vavr.control.Option.of;

@Component
@RequiredArgsConstructor
class NotebookReadModel implements NotebookViews {

    private final JdbcTemplate views;

    @Override
    public Option<NotebookView> findBy(NotebookId notebookId) {
        return Try.ofSupplier(() -> of(views.queryForObject("SELECT notebook_id, notebook_name" +
                " FROM notebook_view" +
                " WHERE notebook_id = ?", new BeanPropertyRowMapper<>(NotebookView.class), notebookId.getId())))
                .getOrElse(none());
    }

    @Override
    public List<NoteSimpleView> fetchNotesFor(NotebookId notebookId) {
        return ofAll(views.query("SELECT note_id, note_name, note_type, created_by, created_at, modified_by, modified_at" +
                " FROM note_view" +
                " WHERE notebook_id = ?", new BeanPropertyRowMapper<>(NoteSimpleView.class), notebookId.getId()));
    }

    @EventListener
    void handle(NotebookCreated event) {
        views.update("INSERT INTO notebook_view" +
                " (id, notebook_id, notebook_name, owner_id)" +
                " VALUES (nextval('notebook_view_seq'), ?, ?, ?)",
                event.getNotebookId(),
                event.getNotebookName(),
                event.getCreatorId());
    }
}
