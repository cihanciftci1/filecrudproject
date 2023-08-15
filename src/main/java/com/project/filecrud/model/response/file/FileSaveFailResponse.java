package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import org.springframework.http.HttpStatus;

public class FileSaveFailResponse extends BaseResponse {
    public FileSaveFailResponse(String message){
        super(message);
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.setSuccess(false);
    }
}
