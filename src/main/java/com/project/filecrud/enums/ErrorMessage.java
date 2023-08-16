package com.project.filecrud.enums;

public enum ErrorMessage {
    INVALID_FILE_SIZE("File size cannot be greater than 5 MB!"),
    INVALID_FILE_EXTENSION("Invalid file extension!"),
    SAVE_EXCEPTION("An error occurred while trying to save file!");

    private final String value;

    ErrorMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
