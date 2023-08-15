package com.project.filecrud.enums;

public enum ExceptionMessage {
    INVALID_FILE_SIZE("File size cannot be greater than 5 MB!"),
    INVALID_FILE_EXTENSION("Invalid file extension!");

    private final String value;

    ExceptionMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
