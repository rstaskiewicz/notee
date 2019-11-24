package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.commons.policies.Allowance;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteId;
import io.vavr.Function3;
import io.vavr.collection.List;
import io.vavr.control.Either;

import static com.gitlab.lamapizama.notee.commons.policies.Rejection.withReason;
import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

interface NoteDeletingPolicy extends Function3<Notebook, CreatorId, NoteId, Either<Rejection, Allowance>> {

    NoteDeletingPolicy noteMustBeEnteredInNotebook = (Notebook notebook, CreatorId creatorId, NoteId noteId) -> {
        if (!notebook.hasEntryWithId(noteId)) {
            return left(withReason("Note is not entered in the notebook"));
        }
        return right(new Allowance());
    };

    NoteDeletingPolicy onlyNotebookOwnerCanDeleteNote = (Notebook notebook, CreatorId creatorId, NoteId noteId) -> {
        if (!notebook.isOwner(creatorId)) {
            return left(withReason("Only notebook owner can delete note"));
        }
        return right(new Allowance());
    };

    static List<NoteDeletingPolicy> allCurrentPolicies() {
        return List.of(
                noteMustBeEnteredInNotebook,
                onlyNotebookOwnerCanDeleteNote);
    }
}
