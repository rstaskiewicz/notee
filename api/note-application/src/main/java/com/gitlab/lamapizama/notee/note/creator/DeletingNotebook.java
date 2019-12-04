package com.gitlab.lamapizama.notee.note.creator;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.note.Authentication;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.gitlab.lamapizama.notee.commons.commands.Result.Rejection;
import static com.gitlab.lamapizama.notee.commons.commands.Result.Success;
import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookDeleted;
import static com.gitlab.lamapizama.notee.note.creator.CreatorEvent.NotebookDeletingFailed;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;

@Service
@RequiredArgsConstructor
public class DeletingNotebook {

    private final Creators creators;
    private final Authentication authentication;

    public Try<Result> delete(@NonNull DeleteNotebook command) {
        return Try.of(() -> {
            Creator creator = find(command.getCreatorId());
            Either<NotebookDeletingFailed, NotebookDeleted> result = creator.deleteNotebook(command.getNotebookId());
            return Match(result).of(
                    Case($Left($()), this::publishEvents),
                    Case($Right($()), this::publishEvents)
            );
        });
    }

    private Result publishEvents(NotebookDeleted notebookCreated) {
        creators.publish(notebookCreated);
        return Success;
    }

    private Result publishEvents(NotebookDeletingFailed notebookCreationFailed) {
        creators.publish(notebookCreationFailed);
        return Rejection;
    }

    private Creator find(CreatorId creatorId) {
        return creators.findBy(creatorId)
                .getOrElseThrow(() -> new ResourceNotFoundException("Creator with given id does not exists: " + creatorId.getId()));
    }

    private Creator getCurrentCreator() {
        return creators.findBy(authentication.getCurrentCreatorId())
                .getOrElseThrow(() -> new IllegalStateException("Creator is not present in the authentication context"));
    }
}
