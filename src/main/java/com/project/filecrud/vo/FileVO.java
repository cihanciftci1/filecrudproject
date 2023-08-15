package com.project.filecrud.vo;

import com.project.filecrud.enums.FileExtension;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class FileVO {
    private Integer id;

    private String name;

    private long size;

    private String extension;

    private byte[] bytes;
}
