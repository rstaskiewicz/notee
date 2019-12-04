package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import com.gitlab.lamapizama.notee.note.note.NoteType;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteDeleted;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteDeletingFailed;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteEntered;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteEnteringFailed;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceFailure;
import static com.gitlab.lamapizama.notee.commons.events.EitherResult.announceSuccess;

@EqualsAndHashCode(of = "notebook")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Notebook {

    @NonNull
    NotebookInformation notebook;

    @NonNull
    List<NoteEnteringPolicy> noteEnteringPolicies;

    @NonNull
    List<NoteDeletingPolicy> noteDeletingPolicies;

    @NonNull
    CreatorId owner;

    @NonNull
    NotebookEntries entries;

    public Either<NoteEnteringFailed, NoteEntered> enterNote(NoteName noteName, NoteType noteType, CreatorId creatorId) {
        Option<Rejection> rejection = noteCanBeEntered(creatorId, noteName);
        if (rejection.isEmpty()) {
            return announceSuccess(NoteEntered.now(generateNoteId(), noteName, noteType, creatorId, notebookId()));
        }
        return announceFailure(NoteEnteringFailed.now(rejection.get(), notebookId(), creatorId));
    }

    public Either<NoteDeletingFailed, NoteDeleted> deleteNote(NoteId noteId, CreatorId creatorId) {
        Option<Rejection> rejection = noteCanBeDeleted(creatorId, noteId);
        if (rejection.isEmpty()) {
            return announceSuccess(NoteDeleted.now(noteId, notebookId()));
        }
        return announceFailure(NoteDeletingFailed.now(rejection.get(), noteId, notebookId()));
    }

    NotebookId notebookId() {
        return notebook.getNotebookId();
    }

    NotebookName name() {
        return notebook.getName();
    }

    CreatorId owner() {
        return owner;
    }

    boolean isOwner(CreatorId creatorId) {
        return owner.equals(creatorId);
    }

    boolean hasEntryNamed(NoteName noteName) {
        return entries.withName(noteName);
    }

    boolean hasEntryWithId(NoteId noteId) {
        return entries.withId(noteId);
    }

    private NoteId generateNoteId() {
        return new NoteId(UUID.randomUUID());
    }

    private Option<Rejection> noteCanBeEntered(CreatorId creatorId, NoteName noteName) {
        return noteEnteringPolicies
                .toStream()
                .map(policy -> policy.apply(this, creatorId, noteName))
                .find(Either::isLeft)
                .map(Either::getLeft);
    }

    private Option<Rejection> noteCanBeDeleted(CreatorId creatorId, NoteId noteId) {
        return noteDeletingPolicies
                .toStream()
                .map(policy -> policy.apply(this, creatorId, noteId))
                .find(Either::isLeft)
                .map(Either::getLeft);
    }
}
