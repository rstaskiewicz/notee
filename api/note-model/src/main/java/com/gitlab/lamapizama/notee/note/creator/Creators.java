package com.gitlab.lamapizama.notee.note.creator;

import io.vavr.control.Option;

interface Creators {

    Option<Creator> findBy(CreatorId creatorId);

    Creator publish(CreatorEvent creatorEvent);
}
