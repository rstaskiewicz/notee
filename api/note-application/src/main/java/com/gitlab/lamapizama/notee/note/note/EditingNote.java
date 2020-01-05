package com.gitlab.lamapizama.notee.note.note;

import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.note.Authentication;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import com.gitlab.lamapizama.notee.note.creatorprofile.CreatorViews;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteEdited;
import com.gitlab.lamapizama.notee.note.note.NoteEvent.NoteEditingFailed;
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
public class EditingNote {

    private final Notes notes;
    private final CreatorViews creatorViews;
    private final Authentication authentication;

    public Try<Result> edit(@NonNull EditNote command) {
        return Try.of(() -> {
            CreatorId creatorId = authentication.getCurrentCreatorId();
            Note note = notes.getBy(command.getNoteId());
            if (note.isPrivate()) {
                authentication.checkIfActionAllowed(note.owner(), creatorViews.findFriendEmailsFor(creatorId).asJava());
            }
            Either<NoteEditingFailed, NoteEdited> result = note.edit(command.getNoteContent(), creatorId);
            return Match(result).of(
                    Case($Left($()), this::publishEvents),
                    Case($Right($()), this::publishEvents));
        });
    }

    private Result publishEvents(NoteEdited event) {
        notes.publish(event);
        return Success;
    }

    private Result publishEvents(NoteEditingFailed event) {
        notes.publish(event);
        return Rejection;
    }
}

