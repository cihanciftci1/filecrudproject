package com.project.filecrud.converter;

import com.project.filecrud.vo.FileVO;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.function.Function;

@Component
public class RequestToFileVOConverter implements Function<MultipartFile, FileVO>, Converter<MultipartFile, FileVO> {
    @SneakyThrows
    @Override
    public FileVO apply(MultipartFile file) {
        return FileVO.builder()
                .name(file.getName())
                .size(file.getSize())
                .extension(Objects.nonNull(file.getOriginalFilename()) ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1) : "")
                .bytes(file.getBytes()).build();
    }

    @Override
    public FileVO convert(MultipartFile source) {
        return apply(source);
    }
}
