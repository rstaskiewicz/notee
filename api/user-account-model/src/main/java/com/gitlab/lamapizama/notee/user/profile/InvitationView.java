package com.gitlab.lamapizama.notee.user.profile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class InvitationView {
    @NonNull String invitingEmail;
    @NonNull Boolean visited;
}
