package com.gitlab.lamapizama.notee.note.creatorprofile;

import com.gitlab.lamapizama.notee.note.creator.CreatorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
class CreatorView {

    @NonNull String creatorId;
    @NonNull String username;
    @NonNull String avatarUrl;
    @NonNull CreatorType creatorType;

    public void setCreatorType(String creatorType) {
        this.creatorType = CreatorType.valueOf(creatorType);
    }
}
