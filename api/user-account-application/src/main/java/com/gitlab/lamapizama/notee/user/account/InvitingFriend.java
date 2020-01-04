package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.commands.Result;
import com.gitlab.lamapizama.notee.commons.exceptions.ActionForbiddenException;
import com.gitlab.lamapizama.notee.commons.exceptions.ResourceNotFoundException;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.FriendAccepted;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.FriendAcceptingFailed;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.FriendInvited;
import com.gitlab.lamapizama.notee.user.account.UserAccountEvent.FriendInvitingFailed;
import io.vavr.control.Either;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static com.gitlab.lamapizama.notee.commons.commands.Result.Rejection;
import static com.gitlab.lamapizama.notee.commons.commands.Result.Success;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;

@RequiredArgsConstructor
public class InvitingFriend {

    private final UserAccounts userAccounts;
    private final AuthenticationFacade authentication;

    public Result invite(@NonNull InviteFriendCommand command) {
        UserAccount userAccount = find(new UserEmail(authentication.getCurrentUserEmail()));
        UserAccount friendAccount = find(command.getFriendEmail());
        Either<FriendInvitingFailed, FriendInvited> result = userAccount.inviteFriend(friendAccount);
        return Match(result).of(
                Case($Left($()), this::publishEvent),
                Case($Right($()), this::publishEvent));
    }

    public Result accept(@NonNull AcceptFriendCommand command) {
        if (!authentication.isActionAllowedFor(command.getUserEmail().getEmail())) {
            throw new ActionForbiddenException("Only account owner can accept friend invitations");
        }
        UserAccount userAccount = find(command.getUserEmail());
        UserAccount friendAccount = find(command.getInvitingEmail());
        Either<FriendAcceptingFailed, FriendAccepted> result = userAccount.acceptFriend(friendAccount);
        return Match(result).of(
                Case($Left($()), this::publishEvent),
                Case($Right($()), this::publishEvent));
    }

    private Result publishEvent(FriendInvited friendAdded) {
        userAccounts.publish(friendAdded);
        return Success;
    }

    private Result publishEvent(FriendInvitingFailed friendAddingFailed) {
        userAccounts.publish(friendAddingFailed);
        return Rejection;
    }

    private Result publishEvent(FriendAccepted friendAccepted) {
        userAccounts.publish(friendAccepted);
        return Success;
    }

    private Result publishEvent(FriendAcceptingFailed friendAcceptingFailed) {
        userAccounts.publish(friendAcceptingFailed);
        return Success;
    }

    private UserAccount find(UserEmail email) {
        return userAccounts.findBy(email)
                .getOrElseThrow(() -> new ResourceNotFoundException("User account does not exists:" + email.getEmail()));
    }
}

@Value
class InviteFriendCommand {
    @NonNull UserEmail friendEmail;
}

@Value
class AcceptFriendCommand {
    @NonNull UserEmail userEmail;
    @NonNull UserEmail invitingEmail;
}
