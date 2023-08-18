package com.project.filecrud.converter;

import com.project.filecrud.dto.FileDTO;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

@Component
public class RequestToFileDTOConverter implements Function<MultipartFile, FileDTO>, Converter<MultipartFile, FileDTO> {
    @SneakyThrows
    @Override
    public FileDTO apply(MultipartFile file) {
        return FileDTO.builder()
                .name(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")))
                .size(file.getSize())
                .extension(Objects.nonNull(file.getOriginalFilename()) ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1) : "")
                .bytes(Arrays.toString(file.getBytes()))
                .build();
    }

    @Override
    public FileDTO convert(MultipartFile source) {
        return apply(source);
    }
}
