package com.project.filecrud.enums;

public enum SuccessMessage {
    FILE_SAVE_SUCCESS("File saved successfully!"),
    FILE_UPDATE_SUCCESS("File updated successfully!"),
    FILE_RETRIEVE_SUCCESS("File retrieved successfully!"),
    FILE_DELETE_SUCCESS("File deleted successfully");

    private final String value;

    SuccessMessage(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
