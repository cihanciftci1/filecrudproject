package com.project.filecrud.converter;

import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.FileSaveSuccessResponse;
import com.project.filecrud.vo.FileVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FileVOToResponseConverter implements Function<FileVO, BaseResponse>, Converter<FileVO, BaseResponse> {
    @Override
    public BaseResponse apply(FileVO fileVO) {
        return new FileSaveSuccessResponse(SuccessMessage.FILE_SAVE_SUCCESS.getValue(), fileVO);
    }

    @Override
    public BaseResponse convert(FileVO source) {
        return apply(source);
    }
}
