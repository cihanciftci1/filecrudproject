package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import org.springframework.http.HttpStatus;

public class FileDeleteFailResponse extends BaseResponse {

    public FileDeleteFailResponse(String message){
        super(message);
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.setSuccess(true);
    }
}