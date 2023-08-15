package com.project.filecrud.validator;

import com.project.filecrud.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileValidator {
    void validate(FileVO file);
}
