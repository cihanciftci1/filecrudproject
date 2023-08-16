package com.project.filecrud.converter;

import com.project.filecrud.entity.File;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.FileSaveSuccessResponse;
import com.project.filecrud.vo.FileVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FileToVOConverter implements Function<File, FileVO>, Converter<File, FileVO> {
    @Override
    public FileVO apply(File file) {
        return FileVO.builder()
                .id(file.getId())
                .name(file.getName())
                .size(file.getSize())
                .extension(file.getExtension().toString())
                .bytes(file.getBytes())
                .build();
    }

    @Override
    public FileVO convert(File source) {
        return apply(source);
    }
}
