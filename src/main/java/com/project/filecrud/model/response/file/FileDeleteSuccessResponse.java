package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class FileDeleteSuccessResponse extends BaseResponse {
    private Map<String, Integer> data;

    public FileDeleteSuccessResponse(String message, Integer id){
        super(message);
        this.data = Map.of("fileId", id);
        this.setStatus(HttpStatus.OK);
        this.setSuccess(true);
    }
}
