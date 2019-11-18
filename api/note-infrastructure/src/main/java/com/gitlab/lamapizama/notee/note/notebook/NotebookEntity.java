package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@EqualsAndHashCode(of = {"notebookId", "creatorId"})
@NoArgsConstructor(access = AccessLevel.PACKAGE)
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

    Notebook toDomainModel() {
        return new Notebook(new NotebookId(notebookId), new NotebookName(notebookName), new CreatorId(creatorId));
    }
}
