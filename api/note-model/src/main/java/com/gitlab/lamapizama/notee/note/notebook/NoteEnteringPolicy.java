package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.commons.policies.Allowance;
import com.gitlab.lamapizama.notee.commons.policies.Rejection;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteName;
import io.vavr.Function3;
import io.vavr.collection.List;
import io.vavr.control.Either;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

interface NoteEnteringPolicy extends Function3<Notebook, CreatorId, NoteName, Either<Rejection, Allowance>> {

    NoteEnteringPolicy cannotEnterAnotherNoteWithTheSameName = (Notebook notebook, CreatorId creatorId, NoteName noteName) -> {
        if (notebook.hasEntryNamed(noteName)) {
            return left(Rejection.withReason("Cannot enter another note with the same name"));
        }
        return right(new Allowance());
    };

    NoteEnteringPolicy onlyNotebookOwnerCanEnterNotes = (Notebook notebook, CreatorId creatorId, NoteName noteName) -> {
        if (!notebook.isOwner(creatorId)) {
            return left(Rejection.withReason("Only notebook owner can enter notes"));
        }
        return right(new Allowance());
    };

    static List<NoteEnteringPolicy> allCurrentPolicies() {
        return List.of(
                cannotEnterAnotherNoteWithTheSameName,
                onlyNotebookOwnerCanEnterNotes);
    }
}
