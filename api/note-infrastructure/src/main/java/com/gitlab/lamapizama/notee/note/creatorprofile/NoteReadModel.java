package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.note.NoteEvent;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCommented;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteCreated;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteEdited;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteRestored;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteTagged;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static io.vavr.collection.List.ofAll;
import static io.vavr.control.Option.none;
import static io.vavr.control.Option.of;
import static java.sql.Timestamp.from;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class NoteReadModel implements NoteViews {

    static String GET_NOTE = "SELECT note_id, note_name, note_type, note_content, notebook_id, created_by," +
            " created_at, modified_by, modified_at" +
            " FROM note_view" +
            " WHERE note_id = ?";

    static String FIND_COMMENTS_FOR_NOTE = "SELECT comment_id, comment_content, note_id, created_at, modified_at" +
            " FROM comment_view" +
            " WHERE note_id = ?";

    static String FIND_TAGS_FOR_NOTE = "SELECT tag_value, note_id" +
            " FROM tag_view" +
            " WHERE note_id = ?";

    static String FIND_ACTIVITIES_FOR_NOTE = "SELECT event_id, activity_type, note_id, creator_id, occurred_at  " +
            " FROM note_activity_view" +
            " WHERE note_id = ?";

    static String CREATE_NOTE = "INSERT INTO note_view" +
            " (id, note_id, note_name, note_type, note_content, notebook_id, created_by, modified_by, created_at, modified_at)" +
            " VALUES (nextVal('note_view_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    static String EDIT_NOTE = "UPDATE note_view" +
            " SET note_content = ?, modified_by = ?, modified_at = ?" +
            " WHERE note_id = ?";

    static String REGISTER_ACTIVITY = "INSERT INTO note_activity_view" +
            " (id,  event_id, note_id, activity_type, creator_id, occurred_at)" +
            " VALUES (nextVal('note_activity_view_seq'), ?, ?, ?, ?, ?)";

    static String ADD_COMMENT_FOR_NOTE = "INSERT INTO comment_view" +
            " (id,  comment_id, comment_content, note_id, created_at, modified_at)" +
            " VALUES (nextVal('comment_view_seq'), ?, ?, ?, ?, ?)";

    static String ADD_TAG_TO_NOTE = "INSERT INTO tag_view" +
            " (id,  tag_value, note_id)" +
            " VALUES (nextVal('tag_view_seq'), ?, ?)";

    static String SAVE_VERSION = "INSERT INTO note_version_view" +
            " (id,  note_id, version_id, note_content, version_by, created_at)" +
            " VALUES (nextVal('note_version_view_seq'), ?, ?, ? , ?, ?)";

    static String FIND_VERSIONS = "SELECT note_id, note_content, version_id, version_by, created_at" +
            " FROM note_version_view" +
            " WHERE note_id = ?";

    JdbcTemplate views;

    @Override
    public Option<NoteView> findBy(NoteId noteId) {
        return Try.ofSupplier(() -> of(views.queryForObject(GET_NOTE, new BeanPropertyRowMapper<>(NoteView.class), noteId.getId())))
                .getOrElse(none());
    }

    @Override
    public List<CommentView> findCommentsFor(NoteId noteId) {
        return ofAll(views.query(FIND_COMMENTS_FOR_NOTE, new BeanPropertyRowMapper<>(CommentView.class), noteId.getId()));
    }

    @Override
    public List<TagView> findTagsFor(NoteId noteId) {
        return ofAll(views.query(FIND_TAGS_FOR_NOTE, new BeanPropertyRowMapper<>(TagView.class), noteId.getId()));
    }

    @Override
    public List<ActivityView> findActivitiesFor(NoteId noteId) {
        return ofAll(views.query(FIND_ACTIVITIES_FOR_NOTE, new BeanPropertyRowMapper<>(ActivityView.class), noteId.getId()));
    }

    @Override
    public List<NoteVersionView> findVersionsFor(NoteId noteId) {
        return ofAll(views.query(FIND_VERSIONS, new BeanPropertyRowMapper<>(NoteVersionView.class), noteId.getId()));
    }

    @EventListener
    public void handle(NoteCreated event) {
        views.update(CREATE_NOTE,
                event.getNoteId(),
                event.getNoteName(),
                event.getNoteType().toString(),
                "",
                event.getNotebookId(),
                event.getCreatorId(),
                event.getCreatorId(),
                from(event.getWhen()),
                from(event.getWhen()));
        registerActivity(event);
    }

    @EventListener
    public void handle(NoteEdited event) {
        views.update(EDIT_NOTE, event.getNoteContent(),
                event.getCreatorId(),
                from(event.getWhen()),
                event.getNoteId());
        views.update(SAVE_VERSION,
                event.getNoteId(),
                event.getEventId(),
                event.getNoteContent(),
                event.getCreatorId(),
                from(event.getWhen()));
        registerActivity(event);
    }

    @EventListener
    public void handle(NoteCommented event) {
        views.update(ADD_COMMENT_FOR_NOTE,
                event.getCommentId(),
                event.getCommentContent(),
                event.getNoteId(),
                from(event.getWhen()),
                from(event.getWhen()));
        registerActivity(event);
    }

    @EventListener
    public void handle(NoteTagged event) {
        views.update(ADD_TAG_TO_NOTE, event.getTag(), event.getNoteId());
        registerActivity(event);
    }

    @EventListener
    public void handle(NoteRestored event) {
        views.update(EDIT_NOTE, event.getRestoredContent(),
                event.getCreatorId(),
                from(event.getWhen()),
                event.getNoteId());
        registerActivity(event);
    }

    private void registerActivity(NoteEvent event) {
        views.update(REGISTER_ACTIVITY,
                event.getEventId(),
                event.getNoteId(),
                event.getType(),
                event.getCreatorId(),
                from(event.getWhen()));
    }
}