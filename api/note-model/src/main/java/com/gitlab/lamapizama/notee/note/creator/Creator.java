package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated;
import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreationFailed;
import com.gitlab.lamapizama.notee.note.notebook.NotebookId;
import com.gitlab.lamapizama.notee.note.notebook.NotebookName;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceFailure;
import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceSuccess;
import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookDeleted;
import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookDeletingFailed;

@EqualsAndHashCode(of = "creator")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Creator {

    @NonNull
    CreatorInformation creator;

    @NonNull
    CreatorPossessions possessions;

    CreatorId creatorId() {
        return creator.getCreatorId();
    }

    public Either<NotebookCreationFailed, NotebookCreated> createNotebook(NotebookName notebookName) {
        if (!possessions.withName(notebookName)) {
            return announceSuccess(NotebookCreated.now(generateNotebookId(), notebookName, creator.getCreatorId()));
        }
        return announceFailure(NotebookCreationFailed.now(
                Rejection.withReason("Creator cannot create another notebook with the same name"), creator.getCreatorId()));
    }

    public Either<NotebookDeletingFailed, NotebookDeleted> deleteNotebook(NotebookId notebookId) {
        if (possessions.withId(notebookId)) {
            return announceSuccess(NotebookDeleted.now(notebookId, creator.getCreatorId()));
        }
        return announceFailure(NotebookDeletingFailed.now(
                Rejection.withReason("Notebook is not in the possession of creator"), creator.getCreatorId()));
    }

    int numberOfPossessions() {
        return possessions.count();
    }

    private NotebookId generateNotebookId() {
        return new NotebookId(UUID.randomUUID());
    }
}
