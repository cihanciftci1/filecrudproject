package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class FileSaveFailResponse extends BaseResponse {
    public FileSaveFailResponse(String message){
        super(message);
        this.setStatus(HttpStatus.BAD_REQUEST);
        this.setSuccess(false);
    }
}
