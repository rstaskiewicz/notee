package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated;
import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookDeleted;
import io.vavr.API;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;
import static javax.persistence.CascadeType.ALL;

@Entity
@EqualsAndHashCode(of = {"creatorId", "creatorType"})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class CreatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    String creatorId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CreatorType creatorType;

    @OneToMany(mappedBy = "creator", cascade = ALL)
    Set<PossessionEntity> possessions = new HashSet<>();

    CreatorEntity(CreatorId creatorId, CreatorType creatorType) {
        this.creatorId = creatorId.getEmail();
        this.creatorType = creatorType;
    }

    CreatorEntity handle(CreatorEvent event) {
        return API.Match(event).of(
                Case($(instanceOf(NotebookCreated.class)), this::handle),
                Case($(instanceOf(NotebookDeleted.class)), this::handle));
    }

    private CreatorEntity handle(NotebookCreated event) {
        possessions.add(new PossessionEntity(event.getNotebookId(), event.getNotebookName(), this));
        return this;
    }

    private CreatorEntity handle(NotebookDeleted event) {
        possessions.remove(new PossessionEntity(event.getNotebookId(), this));
        return this;
    }
}
