package com.project.filecrud.enums;

public enum SuccessMessage {
    FILE_SAVE_SUCCESS("File saved successfully!");

    private final String value;

    SuccessMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
