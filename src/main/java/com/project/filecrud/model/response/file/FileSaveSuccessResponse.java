package com.project.filecrud.model.response.file;

import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.vo.FileVO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class FileSaveSuccessResponse extends BaseResponse {
    private Map<String, FileVO> data;

    public FileSaveSuccessResponse(String message, FileVO fileVO){
        super(message);
        this.data = Map.of("file", fileVO);
        this.setStatus(HttpStatus.OK);
        this.setSuccess(true);
    }
}
