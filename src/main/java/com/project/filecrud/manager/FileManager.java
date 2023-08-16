package com.project.filecrud.manager;

import com.project.filecrud.converter.RequestToFileVOConverter;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.exception.BadRequestException;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.*;
import com.project.filecrud.service.FileService;
import com.project.filecrud.validator.FileSaveRequestValidator;
import com.project.filecrud.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class FileManager {
    private final FileService fileService;
    private final FileSaveRequestValidator fileSaveRequestValidator;
    private final RequestToFileVOConverter requestToFileVOConverter;


    public BaseResponse save(MultipartFile file){
        FileVO fileVO = requestToFileVOConverter.convert(file);
        try{
            fileSaveRequestValidator.validate(fileVO);
            fileVO = fileService.save(fileVO);
        }catch (BadRequestException e1){
            return new FileSaveFailResponse(e1.getMessage());
        }catch (Exception e){
            return new FileSaveFailResponse(ErrorMessage.UNEXPECTED_ERROR.getValue());
        }

        return new FileSaveSuccessResponse(SuccessMessage.FILE_SAVE_SUCCESS.getValue(), fileVO);
    }

    public BaseResponse retrieve(Integer id){
        FileVO fileVO;
        try{
            fileVO = fileService.retrieve(id);
        }catch (ObjectNotFoundException e1){
            return new FileRetrieveFailResponse(e1.getMessage());
        }
        return new FileRetrieveSuccessResponse(SuccessMessage.FILE_RETRIEVE_SUCCESS.getValue(), fileVO);
    }

    public BaseResponse update(Integer id, MultipartFile file){
        FileVO fileVO = requestToFileVOConverter.convert(file);
        try {
            fileSaveRequestValidator.validate(fileVO);
            fileVO = fileService.update(id, fileVO);
        }catch (BadRequestException | ObjectNotFoundException e1){
            return new FileSaveFailResponse(e1.getMessage());
        } catch (Exception e3){
            return new FileSaveFailResponse(ErrorMessage.UNEXPECTED_ERROR.getValue());
        }
        return new FileSaveSuccessResponse(SuccessMessage.FILE_UPDATE_SUCCESS.getValue(), fileVO);
    }

    public BaseResponse delete(Integer id){
        try{
            fileService.delete(id);
        }catch (EmptyResultDataAccessException e1){
            return new FileDeleteFailResponse(ErrorMessage.FILE_NOT_FOUND.getValue());
        }catch (Exception e2){
            return new FileDeleteFailResponse(ErrorMessage.UNEXPECTED_ERROR.getValue());
        }
        return new FileDeleteSuccessResponse(SuccessMessage.FILE_DELETE_SUCCESS.getValue(), id);
    }
}
