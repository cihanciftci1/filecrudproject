package com.project.filecrud.validator;

import com.project.filecrud.entity.File;
import com.project.filecrud.enums.ExceptionMessage;
import com.project.filecrud.enums.FileExtension;
import com.project.filecrud.vo.FileVO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class FileSaveRequestValidator implements FileValidator{
    @Override
    public void validate(FileVO file){
        if((file.getSize() / 1024 / 1024) > 5) throw new IllegalArgumentException(ExceptionMessage.INVALID_FILE_SIZE.getValue());

        if(!containsFileExtension(file.getExtension())) throw new IllegalArgumentException(ExceptionMessage.INVALID_FILE_EXTENSION.getValue());

    }

    private boolean containsFileExtension(String extension){
        for (FileExtension fileExtension : FileExtension.values()){
            if(fileExtension.name().equals(extension)) return true;
        }
        return false;
    }
}
