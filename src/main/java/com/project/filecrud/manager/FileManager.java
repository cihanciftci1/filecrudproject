package com.project.filecrud.manager;

import com.project.filecrud.converter.FileVOToResponseConverter;
import com.project.filecrud.converter.RequestToFileVOConverter;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.model.exception.BadRequestException;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.FileRetrieveFailResponse;
import com.project.filecrud.model.response.file.FileSaveFailResponse;
import com.project.filecrud.service.FileService;
import com.project.filecrud.validator.FileSaveRequestValidator;
import com.project.filecrud.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class FileManager {
    private final FileService fileService;
    private final FileSaveRequestValidator fileSaveRequestValidator;
    private final FileVOToResponseConverter fileVOToResponseConverter;
    private final RequestToFileVOConverter requestToFileVOConverter;


    public BaseResponse save(MultipartFile file){
        FileVO fileVO = requestToFileVOConverter.convert(file);
        try{
            fileSaveRequestValidator.validate(fileVO);
            fileVO = fileService.save(fileVO);
        }catch (BadRequestException e1){
            return new FileSaveFailResponse(e1.getMessage());
        }catch (Exception e){
            return new FileSaveFailResponse(ErrorMessage.SAVE_EXCEPTION.getValue());
        }

        return fileVOToResponseConverter.convert(fileVO);
    }

    public BaseResponse retrieve(Integer id){
        FileVO fileVO;
        try{
            fileVO = fileService.retrieve(id);
        }catch (ObjectNotFoundException e1){
            return new FileRetrieveFailResponse(e1.getMessage());
        }
        return fileVOToResponseConverter.convert(fileVO);
    }

    public BaseResponse update(Integer id, MultipartFile file){
        FileVO fileVO = requestToFileVOConverter.convert(file);
        try {
            fileSaveRequestValidator.validate(fileVO);
            fileVO = fileService.update(id, fileVO);
        }catch (BadRequestException | ObjectNotFoundException e1){
            return new FileSaveFailResponse(e1.getMessage());
        } catch (Exception e3){
            return new FileSaveFailResponse(ErrorMessage.SAVE_EXCEPTION.getValue());
        }
        return fileVOToResponseConverter.convert(fileVO);
    }
}
