package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteDeleted;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteEntered;
import io.vavr.API;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"notebookId", "notebookName"}))
@NoArgsConstructor
@EqualsAndHashCode(of = {"notebookId", "creatorId"})
class NotebookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    UUID notebookId;

    @Column(nullable = false)
    String notebookName;

    @Column(nullable = false)
    String creatorId;

    @OneToMany(mappedBy = "notebook", cascade = ALL, orphanRemoval = true)
    Set<NotebookEntryEntity> entries = new HashSet<>();

    NotebookEntity(NotebookId notebookId, NotebookName notebookName, CreatorId creatorId) {
        this.notebookId = notebookId.getId();
        this.notebookName = notebookName.getName();
        this.creatorId = creatorId.getId();
    }

    NotebookEntity handle(NotebookEvent event) {
        return API.Match(event).of(
                Case($(instanceOf(NoteEntered.class)), this::handle),
                Case($(instanceOf(NoteDeleted.class)), this::handle));
    }

    private NotebookEntity handle(NoteEntered event) {
        entries.add(new NotebookEntryEntity(event.getNoteId(), event.getNoteName(), this));
        return this;
    }

    private NotebookEntity handle(NoteDeleted event) {
        entries.remove(new NotebookEntryEntity(event.getNoteId(), this));
        return this;
    }
}
