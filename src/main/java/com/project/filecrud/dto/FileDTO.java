package com.project.filecrud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Integer id;

    private String name;

    private long size;

    private String extension;

    private String bytes;
}
