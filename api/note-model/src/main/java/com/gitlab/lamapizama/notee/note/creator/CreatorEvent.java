package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.events.DomainEvent;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

public interface CreatorEvent extends DomainEvent<String> {

    default CreatorId creatorId() {
        return new CreatorId(getCreatorId());
    }

    String getCreatorId();

    default String getAggregateId() {
        return getCreatorId();
    }

    @Value
    class CreatorCreated implements CreatorEvent {
        @NonNull String creatorId;
        @NonNull Instant when;
        @NonNull CreatorType creatorType;

        static CreatorCreated now(CreatorId creatorId, CreatorType type) {
            return new CreatorCreated(creatorId.getId(), Instant.now(), type);
        }
    }

    @Value
    class NotebookCreated implements CreatorEvent {
        @NonNull String creatorId;
        @NonNull Instant when;
        @NonNull UUID notebookId;
        @NonNull String notebookName;

        static NotebookCreated now(NotebookId notebookId, NotebookName notebookName, CreatorId creatorId) {
            return new NotebookCreated(creatorId.getId(), Instant.now(), notebookId.getId(), notebookName.getName());
        }
    }

    @Value
    class NotebookCreationFailed implements CreatorEvent {
        @NonNull String creatorId;
        @NonNull Instant when;
        @NonNull String reason;

        static NotebookCreationFailed now(Rejection rejection, CreatorId creatorId) {
            return new NotebookCreationFailed(creatorId.getId(), Instant.now(), rejection.getReason());
        }
    }

    @Value
    class NotebookDeleted implements CreatorEvent {
        @NonNull String creatorId;
        @NonNull Instant when;
        @NonNull UUID notebookId;
        static NotebookDeleted now(NotebookId notebookId, CreatorId creatorId) {
            return new NotebookDeleted(creatorId.getId(), Instant.now(), notebookId.getId());
        }
    }

    @Value
    class NotebookDeletingFailed implements CreatorEvent {
        @NonNull String creatorId;
        @NonNull Instant when;
        @NonNull String reason;

        static NotebookDeletingFailed now(Rejection rejection, CreatorId creatorId) {
            return new NotebookDeletingFailed(creatorId.getId(), Instant.now(), rejection.getReason());
        }
    }
}
