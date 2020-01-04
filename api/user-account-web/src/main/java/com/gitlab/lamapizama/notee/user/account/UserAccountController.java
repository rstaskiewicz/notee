package com.gitlab.lamapizama.notee.user.account;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.exceptions.ActionForbiddenException;
import com.gitlab.lamapizama.notee.user.account.request.RegisterUserAccountRequest;
import com.gitlab.lamapizama.notee.user.profile.InvitationView;
import com.gitlab.lamapizama.notee.user.profile.UserAccountViews;
import com.gitlab.lamapizama.notee.user.profile.UserProfileView;
import com.gitlab.lamapizama.notee.user.verification.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountMapper mapper;
    private final RegisteringUserAccount registeringUserAccount;
    private final ConfirmingUserRegistration confirmingUserRegistration;
    private final InvitingFriend invitingFriend;
    private final UserAccountViews userAccountViews;
    private final AuthenticationFacade authentication;

    @PostMapping("/register")
    void registerUserAccount(@RequestBody @Valid RegisterUserAccountRequest request) {
        registeringUserAccount.register(mapper.fromCreate(request));
    }

    @GetMapping("/confirm/{email}")
    void confirmUserRegistration(@PathVariable String email, @RequestParam String token) {
        confirmingUserRegistration.confirm(new ConfirmUserRegistrationCommand(
                new UserEmail(email),
                new Token(token)));
    }

    @GetMapping("/profiles")
    ResponseEntity<CollectionModel<EntityModel<UserProfile>>> findUserProfiles() {
        List<UserProfileView> currentUserFriends = userAccountViews
                .findFriendsFor(new UserEmail(authentication.getCurrentUserEmail())).asJava();

        List<EntityModel<UserProfile>> profiles = userAccountViews.findUserAccounts()
                .map(userProfile -> userProfile.withIsMyself(authentication.getCurrentUserEmail().equals(userProfile.getUserEmail())))
                .map(userProfile -> userProfile.withIsFriend(currentUserFriends.contains(userProfile)))
                .map(UserProfile::fromView)
                .map(this::userProfileViewWithLinkToSelf)
                .collect(toList());
        return ok(new CollectionModel<>(profiles, linkTo(methodOn(UserAccountController.class).findUserProfiles()).withSelfRel()));
    }

    @GetMapping("/profiles/{userEmail}")
    public ResponseEntity<EntityModel<UserProfile>> findUserProfile(@PathVariable String userEmail) {
        List<UserProfileView> currentUserFriends = userAccountViews
                .findFriendsFor(new UserEmail(authentication.getCurrentUserEmail())).asJava();

        return userAccountViews.findUserAccount(new UserEmail(userEmail))
                .map(userProfile -> userProfile.withIsMyself(authentication.getCurrentUserEmail().equals(userProfile.getUserEmail())))
                .map(userProfile -> userProfile.withIsFriend(currentUserFriends.contains(userProfile)))
                .map(UserProfile::fromView)
                .map(userProfile -> ok(userProfileViewWithLinkToSelf(userProfile)))
                .getOrElse(status(INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/profiles/{userEmail}")
    ResponseEntity<?> inviteFriend(@PathVariable String userEmail) {
        invitingFriend.invite(new InviteFriendCommand(
                new UserEmail(userEmail)));
        return ok().build();
    }

    @GetMapping("/profiles/{userEmail}/invitations")
    ResponseEntity<CollectionModel<EntityModel<InvitationView>>> findInvitations(@PathVariable String userEmail,
                                                                                 @RequestParam(required = false) boolean onlyNew) {
        if (!authentication.isActionAllowedFor(userEmail)) {
            throw new ActionForbiddenException("Action forbidden");
        }
        List<EntityModel<InvitationView>> invitations = userAccountViews.findInvitationsFor(new UserEmail(userEmail), onlyNew)
                .map(invitationView -> invitationViewWithLinkToSelf(userEmail, invitationView))
                .collect(toList());
        userAccountViews.markInvitationsAsVisitedFor(new UserEmail(userEmail));
        return ok(new CollectionModel<>(invitations, linkTo(methodOn(UserAccountController.class).findInvitations(userEmail, false)).withSelfRel()));
    }

    @GetMapping("/profiles/{userEmail}/invitations/{invitingEmail}")
    ResponseEntity<EntityModel<InvitationView>> findInvitation(@PathVariable String userEmail, @PathVariable String invitingEmail) {
        if (!authentication.isActionAllowedFor(userEmail)) {
            throw new ActionForbiddenException("Action forbidden");
        }
        return userAccountViews.findInvitationsFor(new UserEmail(userEmail), false)
                .find(invitationView -> invitationView.getInvitingEmail().equals(invitingEmail))
                .map(invitationView -> ok(invitationViewWithLinkToSelf(userEmail, invitationView)))
                .getOrElse(status(INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/profiles/{userEmail}/invitations/{invitingEmail}")
    ResponseEntity<?> acceptInvitation(@PathVariable String userEmail, @PathVariable String invitingEmail) {
        if (!authentication.isActionAllowedFor(userEmail)) {
            throw new ActionForbiddenException("Action forbidden");
        }
        invitingFriend.accept(new AcceptFriendCommand(
                new UserEmail(userEmail),
                new UserEmail(invitingEmail)));
        return ok().build();
    }

    @GetMapping("/profiles/{userEmail}/friends")
    ResponseEntity<CollectionModel<EntityModel<UserProfile>>> findFriends(@PathVariable String userEmail) {
        io.vavr.collection.List<UserProfileView> currentUserFriends = userAccountViews
                .findFriendsFor(new UserEmail(authentication.getCurrentUserEmail()));

        io.vavr.collection.List<UserProfileView> friendsFor = userAccountViews.findFriendsFor(new UserEmail(userEmail))
                .map(userProfile -> userProfile.withIsMyself(authentication.getCurrentUserEmail().equals(userProfile.getUserEmail())))
                .map(userProfile -> userProfile.withIsFriend(currentUserFriends.contains(userProfile)));
        if (!authentication.isActionAllowedFor(userEmail, currentUserFriends.map(UserProfileView::getUserEmail).asJava())) {
            throw new ActionForbiddenException("Action forbidden");
        }
        List<EntityModel<UserProfile>> friends = friendsFor
                .map(UserProfile::fromView)
                .map(this::userProfileViewWithLinkToSelf)
                .collect(toList());
        return ok(new CollectionModel<>(friends, linkTo(methodOn(UserAccountController.class).findFriends(userEmail)).withSelfRel()));
    }

    private EntityModel<UserProfile> userProfileViewWithLinkToSelf(UserProfile userProfile) {
        Link linkToSelf = linkTo(methodOn(UserAccountController.class).findUserProfile(userProfile.getUserEmail()))
                .withSelfRel();
        if (!userProfile.getIsMyself() && !userProfile.getIsFriend()) {
            linkToSelf.andAffordance(afford(methodOn(UserAccountController.class)
                    .inviteFriend(userProfile.getUserEmail())));
        }

        EntityModel<UserProfile> userProfileModel = new EntityModel<>(userProfile, linkToSelf);
        if (userProfile.getIsMyself()) {
            userProfileModel.add(linkTo(methodOn(UserAccountController.class)
                    .findInvitations(userProfile.getUserEmail(), false)).withRel("invitations"));
            userProfileModel.add(linkTo(methodOn(UserAccountController.class)
                    .findInvitations(userProfile.getUserEmail(), true)).withRel("new-invitations"));
        }
        if (userProfile.getIsMyself() || userProfile.getIsFriend()) {
            userProfileModel.add(linkTo(methodOn(UserAccountController.class)
                    .findFriends(userProfile.getUserEmail())).withRel("friends"));
        }
        return userProfileModel;
    }

    private EntityModel<InvitationView> invitationViewWithLinkToSelf(String userEmail, InvitationView invitation) {
        return new EntityModel<>(
                invitation,
                linkTo(methodOn(UserAccountController.class).findInvitation(userEmail, invitation.getInvitingEmail()))
                        .withSelfRel()
                        .andAffordance(afford(methodOn(UserAccountController.class)
                                .acceptInvitation(userEmail, invitation.getInvitingEmail()))));
    }
}
