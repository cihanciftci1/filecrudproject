package com.project.filecrud.enums;

public enum SuccessMessage {
    FILE_SAVE_SUCCESS("File save successful!"),
    FILE_UPDATE_SUCCESS("File update successful!"),
    FILE_RETRIEVE_SUCCESS("File retrieve successful!"),
    FILE_DELETE_SUCCESS("File delete successful"),
    USER_REGISTERED("User register successful"),
    USER_LOGGED_IN("User login successful");

    private final String value;

    SuccessMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
