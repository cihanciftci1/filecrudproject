package com.project.filecrud.validator;

import com.project.filecrud.dto.FileDTO;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.FileExtension;
import com.project.filecrud.model.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class FileSaveRequestValidator {
    public void validate(FileDTO file){
        if((file.getSize() / 1024 / 1024) > 5) throw new BadRequestException(ErrorMessage.INVALID_FILE_SIZE.getValue());

        if(!containsFileExtension(file.getExtension())) throw new BadRequestException(ErrorMessage.INVALID_FILE_EXTENSION.getValue());

    }

    private boolean containsFileExtension(String extension){
        for (FileExtension fileExtension : FileExtension.values()){
            if(fileExtension.name().equals(extension)) return true;
        }
        return false;
    }
}
