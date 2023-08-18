package com.project.filecrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Integer id;

    private String name;

    private long size;

    private String extension;

    private String bytes;
}
