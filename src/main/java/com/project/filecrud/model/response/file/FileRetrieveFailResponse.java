package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import org.springframework.http.HttpStatus;

public class FileRetrieveFailResponse extends BaseResponse {
    public FileRetrieveFailResponse(String message){
        super(message);
        this.setStatus(HttpStatus.NOT_FOUND);
        this.setSuccess(false);
    }
}
