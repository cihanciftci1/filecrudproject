package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.dto.FileDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class FileRetrieveSuccessResponse extends BaseResponse {
    private Map<String, FileDTO> data;

    public FileRetrieveSuccessResponse(String message, FileDTO fileDTO){
        super(message);
        this.data = Map.of("file", fileDTO);
        this.setStatus(HttpStatus.OK);
        this.setSuccess(true);
    }
}
