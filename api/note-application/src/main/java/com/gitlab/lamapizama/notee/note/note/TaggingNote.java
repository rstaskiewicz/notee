package com.gitlab.lamapizama.notee.note.note;


import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.authentication.UserDetails;
import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteTagged;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteTaggingFailed;
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
public class TaggingNote {

    private final AuthenticationFacade authentication;
    private final Notes notes;

    public Try<Result> tag(@NonNull TagNote command) {
        return Try.of(() -> {
            CreatorId creatorId = getFromContext();
            Note note = notes.getBy(command.getNoteId());
            Either<NoteTaggingFailed, NoteTagged> result = note.tag(command.getTag(), creatorId);
            return Match(result).of(
                    Case($Left($()), this::publishEvents),
                    Case($Right($()), this::publishEvents));
        });
    }

    private Result publishEvents(NoteTagged event) {
        notes.publish(event);
        return Success;
    }

    private Result publishEvents(NoteTaggingFailed event) {
        notes.publish(event);
        return Rejection;
    }

    private CreatorId getFromContext() {
        return authentication.getUserDetails()
                .map(UserDetails::getUserId)
                .map(CreatorId::new)
                .getOrElseThrow(() -> new IllegalStateException("Creator is not present in the authentication context"));
    }
}

