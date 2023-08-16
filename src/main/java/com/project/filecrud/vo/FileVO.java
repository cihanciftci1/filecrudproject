package com.project.filecrud.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileVO {
    private Integer id;

    private String name;

    private long size;

    private String extension;

    private String bytes;
}
