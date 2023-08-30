package com.homestore.utils;

public enum ResponseEnum {
    ALREADY_EXISTS,     // resource already exists
    SAVED,              // resource successfully saved
    DELETED,            // resource successfully deleted
    NOT_FOUND,          // resource not found
    FOUND,              // resource found
    UPDATED,            // resource successfully updated
    ADDED,              // resource successfully added to another
    BAD_REQUEST,        // bad request
    ENABLED,            // user successfully enabled
    SENT,               // email successfully sent
    AUTHENTICATED,      // user successfully authenticated
    PWD_DOES_NOT_MATCH, // given password does not match to user stored password
    CHOOSE_ANOTHER_PWD, // the new password to replace matches the old one
    FORBIDDEN           // request refused for security reasons
}