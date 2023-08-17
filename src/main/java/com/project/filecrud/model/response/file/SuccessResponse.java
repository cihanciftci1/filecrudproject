package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class SuccessResponse extends BaseResponse {
    private Map<String, Object> data;

    public SuccessResponse(String message, Object data){
        super(message);
        this.data = Map.of("data", data);
        this.setStatus(HttpStatus.OK);
        this.setSuccess(true);
    }
}
