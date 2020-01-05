package com.gitlab.lamapizama.notee.note;

import com.gitlab.lamapizama.notee.commons.authentication.AuthenticationFacade;
import com.gitlab.lamapizama.notee.commons.authentication.UserDetails;
import com.gitlab.lamapizama.notee.commons.exceptions.ActionForbiddenException;
import com.gitlab.lamapizama.notee.note.creator.CreatorId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Authentication {

    private final AuthenticationFacade authenticationFacade;

    public CreatorId getCurrentCreatorId() {
        return authenticationFacade.getUserDetails()
                .map(UserDetails::getUserId)
                .map(CreatorId::new)
                .getOrElseThrow(() -> new IllegalStateException("Creator is not present in the authentication context"));
    }

    public void checkIfActionAllowed(CreatorId creatorId) {
        if (!authenticationFacade.isActionAllowed(creatorId.getId())) {
            throw new ActionForbiddenException("Action is forbidden");
        }
    }

    public void checkIfActionAllowed(CreatorId creatorId, List<String> friendEmails) {
        if (!authenticationFacade.isActionAllowed(creatorId.getId(), friendEmails)) {
            throw new ActionForbiddenException("Action is forbidden");
        }
    }
}
