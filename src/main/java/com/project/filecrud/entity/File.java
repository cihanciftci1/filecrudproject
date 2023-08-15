package com.project.filecrud.entity;

import com.project.filecrud.enums.FileExtension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private long size;

    @Enumerated(EnumType.STRING)
    private FileExtension extension;

    @Lob
    private byte[] bytes;
}
