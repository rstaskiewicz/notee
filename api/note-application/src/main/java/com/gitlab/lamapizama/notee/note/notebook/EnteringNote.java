package com.gitlab.lamapizama.notee.note.notebook;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.note.Authentication;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.creatorprofile.CreatorViews;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteEntered;
import com.gitlab.lamapizama.notee.note.notebook.NotebookEvent.NoteEnteringFailed;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.gitlab.lamapizama.notee.commons.commands.Result.Rejection;
import static com.gitlab.lamapizama.notee.commons.commands.Result.Success;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;

@Service
@RequiredArgsConstructor
public class EnteringNote {

    private final Authentication authentication;
    private final Notebooks notebooks;
    private final CreatorViews creatorViews;

    public Try<Result> enter(@NonNull EnterNote command) {
        return Try.of(() -> {
            CreatorId creatorId = authentication.getCurrentCreatorId();
            Notebook notebook = findBy(command.getNotebookId());
            authentication.checkIfActionAllowed(notebook.owner(), creatorViews.findFriendEmailsFor(creatorId).asJava());
            Either<NoteEnteringFailed, NoteEntered> result = notebook.enterNote(
                    command.getNoteName(), command.getNoteType(), creatorId);
            return Match(result).of(
                    Case($Left($()), this::publishEvents),
                    Case($Right($()), this::publishEvents));
        });
    }

    private Result publishEvents(NoteEntered event) {
        notebooks.publish(event);
        return Success;
    }

    private Result publishEvents(NoteEnteringFailed event) {
        notebooks.publish(event);
        return Rejection;
    }


    private Notebook findBy(NotebookId notebookId) {
        return notebooks.findBy(notebookId)
                .getOrElseThrow(() -> new ResourceNotFoundException(("Notebook with given id does not exists: " + notebookId.getId())));
    }
}

