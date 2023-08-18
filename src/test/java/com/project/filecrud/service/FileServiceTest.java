package com.project.filecrud.service;

import com.project.filecrud.converter.FileToDTOConverter;
import com.project.filecrud.dto.FileDTO;
import com.project.filecrud.entity.File;
import com.project.filecrud.enums.FileExtension;
import com.project.filecrud.repository.FileRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    private FileRepository fileRepository;

    @Mock
    private FileToDTOConverter fileToDTOConverter;

    private FileService fileService;

    @BeforeEach
    public void setUp() {
        this.fileService = new FileService(fileRepository, fileToDTOConverter);
    }

    @Test
    public void should_save() {
        //given
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName("test.docx");
        fileDTO.setSize(12L);
        fileDTO.setExtension("docx");
        fileDTO.setBytes(Arrays.toString("Hello, World!".getBytes()));

        File file = File.builder()
                .id(1)
                .name("test.docx")
                .size(12L)
                .extension(FileExtension.docx)
                .bytes("Hello, World!".getBytes()).build();
        when(fileRepository.save(any())).thenReturn(file);

        //when
        FileDTO savedFileDTO = fileService.save(fileDTO);

        //then
        assertNotNull(savedFileDTO.getId());
        assertEquals(fileDTO.getName(), savedFileDTO.getName());
        assertEquals(fileDTO.getSize(), savedFileDTO.getSize());
        assertEquals(fileDTO.getExtension(), savedFileDTO.getExtension());
        assertEquals(fileDTO.getBytes(), savedFileDTO.getBytes());
    }

    @Test
    public void should_retrieve() {
        //given
        int fileId = 1;
        FileDTO expectedFileDTO = new FileDTO();
        File file = new File();

        when(fileRepository.findById(fileId)).thenReturn(Optional.of(file));
        when(fileToDTOConverter.convert(file)).thenReturn(expectedFileDTO);

        //when
        FileDTO retrievedFileDTO = fileService.retrieve(fileId);

        //then
        assertEquals(expectedFileDTO, retrievedFileDTO);
    }

    @Test
    public void should_throw_not_found_exception_at_retrieve() {
        //given
        int fileId = 1;

        //when
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

        //then
        assertThrows(ObjectNotFoundException.class, () -> fileService.retrieve(fileId));
    }

    @Test
    public void should_update() {
        //given
        int fileId = 1;
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName("test.docx");
        fileDTO.setSize(12L);
        fileDTO.setExtension("docx");
        fileDTO.setBytes(Arrays.toString("Hello, World!".getBytes()));

        File existingFile = File.builder()
                .id(1)
                .name("test.docx")
                .size(12L)
                .extension(FileExtension.docx)
                .bytes("Hello, World!".getBytes()).build();

        when(fileRepository.findById(fileId)).thenReturn(Optional.of(existingFile));
        when(fileRepository.save(any())).thenReturn(existingFile);

        //when
        FileDTO updatedFileDTO = fileService.update(fileId, fileDTO);

        //then
        assertEquals(fileDTO.getName(), updatedFileDTO.getName());
        assertEquals(fileDTO.getSize(), updatedFileDTO.getSize());
        assertEquals(fileDTO.getExtension(), updatedFileDTO.getExtension());
        assertEquals(fileDTO.getBytes(), updatedFileDTO.getBytes());
    }

    @Test
    public void should_throw_not_found_exception_at_update() {
        //given
        int fileId = 1;
        FileDTO fileDTO = new FileDTO();
        fileDTO.setName("test.txt");
        fileDTO.setSize(12L);
        fileDTO.setExtension("txt");
        fileDTO.setBytes("Hello, World!");

        //when
        when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

        //then
        assertThrows(ObjectNotFoundException.class, () -> fileService.update(fileId, fileDTO));
    }

    @Test
    public void should_delete() {
        //given
        int fileId = 1;

        //when
        Integer deletedId = fileService.delete(fileId);

        //then
        assertEquals(fileId, deletedId);
        verify(fileRepository, times(1)).deleteById(fileId);
    }
}
