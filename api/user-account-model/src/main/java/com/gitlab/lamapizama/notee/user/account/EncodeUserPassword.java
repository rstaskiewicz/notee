package com.gitlab.lamapizama.notee.user.account;

@FunctionalInterface
interface EncodeUserPassword {

    EncodedPassword encode(RawPassword rawPassword);
}
