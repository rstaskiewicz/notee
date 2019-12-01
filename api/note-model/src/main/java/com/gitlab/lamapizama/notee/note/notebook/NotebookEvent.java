package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.commons.events.DomainEvent;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import com.gitlab.lamapizama.notee.note.note.NoteType;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

public interface NotebookEvent extends DomainEvent<UUID> {

    default NotebookId notebookId() {
        return new NotebookId(getNotebookId());
    }

    UUID getNotebookId();

    default UUID getAggregateId() {
        return getNotebookId();
    }

    @Value
    class NoteEntered implements NotebookEvent {
        @NonNull UUID notebookId;
        @NonNull Instant when;
        @NonNull UUID NoteId;
        @NonNull String noteName;
        @NonNull NoteType noteType;

        static NoteEntered now(NoteId noteId, NoteName noteName, NoteType noteType, NotebookId notebookId) {
            return new NoteEntered(
                    notebookId.getId(),
                    Instant.now(),
                    noteId.getId(),
                    noteName.getName(),
                    noteType);
        }
    }

    @Value
    class NoteEnteringFailed implements NotebookEvent {
        @NonNull UUID notebookId;
        @NonNull Instant when;
        @NonNull String reason;
        @NonNull String creatorId;

        static NoteEnteringFailed now(Rejection rejection, NotebookId notebookId, CreatorId creatorId) {
            return new NoteEnteringFailed(
                    notebookId.getId(),
                    Instant.now(),
                    rejection.getReason(),
                    creatorId.getId());
        }
    }

    @Value
    class NoteDeleted implements NotebookEvent {
        @NonNull UUID notebookId;
        @NonNull Instant when;
        @NonNull UUID noteId;

        static NoteDeleted now(NoteId noteId, NotebookId notebookId) {
            return new NoteDeleted(notebookId.getId(), Instant.now(), noteId.getId());
        }
    }

    @Value
    class NoteDeletingFailed implements NotebookEvent {
        @NonNull UUID notebookId;
        @NonNull Instant when;
        @NonNull String reason;
        @NonNull UUID noteId;

        static NoteDeletingFailed now(Rejection rejection, NoteId noteId, NotebookId notebookId) {
            return new NoteDeletingFailed(notebookId.getId(), Instant.now(), rejection.getReason(), noteId.getId());
        }
    }
}
