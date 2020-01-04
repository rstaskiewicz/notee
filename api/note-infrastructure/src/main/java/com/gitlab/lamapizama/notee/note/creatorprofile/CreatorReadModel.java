package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.CreatorCreated;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.creator.CreatorType;
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
class CreatorReadModel implements CreatorViews {

    private final JdbcTemplate views;

    @Override
    public Option<CreatorView> findBy(CreatorId creatorId) {
        return Try.ofSupplier(() -> of(views.queryForObject("SELECT creator_id, creator_type" +
                " FROM creator_profile" +
                " WHERE creator_id = ?", new BeanPropertyRowMapper<>(CreatorView.class), creatorId.getId())))
                .getOrElse(none());
    }

    @Override
    public List<NotebookView> fetchNotebooksFor(CreatorId creatorId) {
        return ofAll(views.query("SELECT notebook_id, notebook_name" +
                " FROM notebook_view" +
                " WHERE owner_id = ?", new BeanPropertyRowMapper<>(NotebookView.class), creatorId.getId()));
    }

    @EventListener
    void handle(CreatorCreated event) {
        views.update("INSERT INTO creator_profile" +
                        " (id, creator_id, creator_type)" +
                        " VALUES (nextval('creator_profile_seq'), ?, ?)",
                event.getCreatorId(),
                CreatorType.Regular.toString());
    }
}
