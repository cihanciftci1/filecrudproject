package com.project.filecrud.enums;

public enum ErrorMessage {
    INVALID_FILE_SIZE("File size cannot be greater than 5 MB!"),
    INVALID_FILE_EXTENSION("Invalid file extension!"),
    UNEXPECTED_ERROR("An unexpected error occurred!"),
    FILE_NOT_FOUND("File is not found with given id!"),
    USERNAME_TAKEN("Username is already taken!"),
    USERNAME_NOT_FOUND("Username is not found!"),
    USER_UNAUTHORIZED("User is not unauthorized!"),
    INVALID_USERNAME_PASSWORD("Username or password is invalid!");

    private final String value;

    ErrorMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
