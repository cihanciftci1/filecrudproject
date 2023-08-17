package com.project.filecrud.manager;

import com.project.filecrud.converter.RequestToFileDTOConverter;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.exception.BadRequestException;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.*;
import com.project.filecrud.service.FileService;
import com.project.filecrud.validator.FileSaveRequestValidator;
import com.project.filecrud.dto.FileDTO;
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
    private final RequestToFileDTOConverter requestToFileDTOConverter;


    public BaseResponse save(MultipartFile file){
        FileDTO fileDTO = requestToFileDTOConverter.convert(file);
        try{
            fileSaveRequestValidator.validate(fileDTO);
            fileDTO = fileService.save(fileDTO);
        }catch (BadRequestException e1){
            return new BadRequestResponse(e1.getMessage());
        }catch (Exception e){
            return new BadRequestResponse(ErrorMessage.UNEXPECTED_ERROR.getValue());
        }

        return new SuccessResponse(SuccessMessage.FILE_SAVE_SUCCESS.getValue(), fileDTO);
    }

    public BaseResponse retrieve(Integer id){
        FileDTO fileDTO;
        try{
            fileDTO = fileService.retrieve(id);
        }catch (ObjectNotFoundException e1){
            return new NotFoundResponse(e1.getMessage());
        }
        return new SuccessResponse(SuccessMessage.FILE_RETRIEVE_SUCCESS.getValue(), fileDTO);
    }

    public BaseResponse update(Integer id, MultipartFile file){
        FileDTO fileDTO = requestToFileDTOConverter.convert(file);
        try {
            fileSaveRequestValidator.validate(fileDTO);
            fileDTO = fileService.update(id, fileDTO);
        }catch (BadRequestException | ObjectNotFoundException e1){
            return new BadRequestResponse(e1.getMessage());
        } catch (Exception e3){
            return new BadRequestResponse(ErrorMessage.UNEXPECTED_ERROR.getValue());
        }
        return new SuccessResponse(SuccessMessage.FILE_UPDATE_SUCCESS.getValue(), fileDTO);
    }

    public BaseResponse delete(Integer id){
        try{
            fileService.delete(id);
        }catch (EmptyResultDataAccessException e1){
            return new BadRequestResponse(ErrorMessage.FILE_NOT_FOUND.getValue());
        }catch (Exception e2){
            return new BadRequestResponse(ErrorMessage.UNEXPECTED_ERROR.getValue());
        }
        return new SuccessResponse(SuccessMessage.FILE_DELETE_SUCCESS.getValue(), id);
    }
}
