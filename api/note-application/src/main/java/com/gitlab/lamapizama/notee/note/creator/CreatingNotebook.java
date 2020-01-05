package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.note.Authentication;
import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreated;
import com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookCreationFailed;
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
public class CreatingNotebook {

    private final Creators creators;
    private final Authentication authentication;

    public Try<Result> create(@NonNull CreateNotebook command) {
        return Try.of(() -> {
            authentication.checkIfActionAllowed(command.getCreatorId());
            Creator creator = find(command.getCreatorId());
            Either<NotebookCreationFailed, NotebookCreated> result = creator.createNotebook(command.getNotebookName());
            return Match(result).of(
                    Case($Left($()), this::publishEvents),
                    Case($Right($()), this::publishEvents)
            );
        });
    }

    private Result publishEvents(NotebookCreated notebookCreated) {
        creators.publish(notebookCreated);
        return Success;
    }

    private Result publishEvents(NotebookCreationFailed notebookCreationFailed) {
        creators.publish(notebookCreationFailed);
        return Rejection;
    }

    private Creator find(CreatorId creatorId) {
        return creators.findBy(creatorId)
                .getOrElseThrow(() -> new ResourceNotFoundException(("Creator with given id does not exists: " + creatorId.getId())));
    }
}

