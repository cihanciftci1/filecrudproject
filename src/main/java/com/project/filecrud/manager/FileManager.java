package com.project.filecrud.manager;

import com.project.filecrud.converter.FileVOToResponseConverter;
import com.project.filecrud.converter.RequestToFileVOConverter;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.FileSaveFailResponse;
import com.project.filecrud.service.FileService;
import com.project.filecrud.validator.FileSaveRequestValidator;
import com.project.filecrud.vo.FileVO;
import lombok.RequiredArgsConstructor;
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
        }catch (Exception e){
            return new FileSaveFailResponse(e.getMessage());
        }

        return fileVOToResponseConverter.convert(fileService.save(fileVO));
    }
}
