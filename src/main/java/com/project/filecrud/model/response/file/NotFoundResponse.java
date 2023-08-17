package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundResponse extends BaseResponse {
    public NotFoundResponse(String message){
        super(message);
        this.setStatus(HttpStatus.NOT_FOUND);
        this.setSuccess(false);
    }
}
