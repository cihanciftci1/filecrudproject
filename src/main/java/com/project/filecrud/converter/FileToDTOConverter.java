package com.project.filecrud.converter;

import com.project.filecrud.entity.File;
import com.project.filecrud.dto.FileDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FileToDTOConverter implements Function<File, FileDTO>, Converter<File, FileDTO> {
    @Override
    public FileDTO apply(File file) {
        return FileDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .size(file.getSize())
                .extension(file.getExtension().toString())
                .bytes(ByteArrayConverter.convertBytesToStringBytes(file.getBytes()))
                .build();
    }

    @Override
    public FileDTO convert(File source) {
        return apply(source);
    }
}
