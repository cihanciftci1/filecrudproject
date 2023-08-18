package com.project.filecrud.manager;

import com.project.filecrud.converter.RequestToFileDTOConverter;
import com.project.filecrud.dto.FileDTO;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.exception.BadRequestException;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.BadRequestResponse;
import com.project.filecrud.model.response.file.NotFoundResponse;
import com.project.filecrud.model.response.file.SuccessResponse;
import com.project.filecrud.service.FileService;
import com.project.filecrud.validator.FileSaveRequestValidator;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileManagerTest {

    @Mock
    private FileService fileService;

    @Mock
    private FileSaveRequestValidator fileSaveRequestValidator;

    @Mock
    private RequestToFileDTOConverter requestToFileDTOConverter;


    private FileManager fileManager;

    @BeforeEach
    public void setUp() {
        this.fileManager = new FileManager(fileService, fileSaveRequestValidator, requestToFileDTOConverter);
    }

    @Test
    public void should_save() {
        //given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.docx", "text/plain", "Hello, World!".getBytes());
        FileDTO fileDTO = FileDTO.builder()
                .id(1)
                .name("test.docx")
                .size(12L)
                .extension("docx")
                .bytes("Hello, World!")
                .build();


        //when
        when(requestToFileDTOConverter.convert(multipartFile)).thenReturn(fileDTO);
        when(fileService.save(fileDTO)).thenReturn(fileDTO);
        BaseResponse response = fileManager.save(multipartFile);

        //then
        assertEquals(SuccessResponse.class, response.getClass());
        assertEquals(SuccessMessage.FILE_SAVE_SUCCESS.getValue(), response.getMessage());
        assertEquals(fileDTO, ((SuccessResponse) response).getData().get("data"));
    }

    @Test
    public void should_give_bad_request_response_at_save() {
        //given
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        FileDTO fileDTO = FileDTO.builder()
                .id(1)
                .name("test.txt")
                .size(12L)
                .extension("txt")
                .bytes("Hello, World!")
                .build();


        //when
        when(requestToFileDTOConverter.convert(multipartFile)).thenReturn(fileDTO);
        doThrow(new BadRequestException(ErrorMessage.INVALID_FILE_EXTENSION.getValue())).when(fileSaveRequestValidator).validate(fileDTO);
        BaseResponse response = fileManager.save(multipartFile);

        //then
        assertEquals(BadRequestResponse.class, response.getClass());
        assertEquals(ErrorMessage.INVALID_FILE_EXTENSION.getValue(), response.getMessage());
    }

    @Test
    public void should_retrieve() {
        //given
        int fileId = 1;
        FileDTO fileDTO = FileDTO.builder()
                .id(fileId)
                .name("test.docx")
                .size(12L)
                .extension("docx")
                .bytes("Hello, World!")
                .build();


        //when
        when(fileService.retrieve(fileId)).thenReturn(fileDTO);
        BaseResponse response = fileManager.retrieve(fileId);

        //then
        assertEquals(SuccessResponse.class, response.getClass());
        assertEquals(SuccessMessage.FILE_RETRIEVE_SUCCESS.getValue(), response.getMessage());
        assertEquals(fileDTO, ((SuccessResponse) response).getData().get("data"));
    }

    @Test
    public void should_return_file_not_found_response_at_retrieve() {
        //given
        int fileId = 1;

        //when
        doThrow(new ObjectNotFoundException(fileId, ErrorMessage.FILE_NOT_FOUND.getValue())).when(fileService).retrieve(fileId);
        BaseResponse response = fileManager.retrieve(fileId);

        //then
        assertEquals(NotFoundResponse.class, response.getClass());
    }

    @Test
    public void should_update() {
        //given
        int fileId = 1;
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.docx", "text/plain", "Hello, World!".getBytes());
        FileDTO fileDTO = FileDTO.builder()
                .id(fileId)
                .name("test.docx")
                .size(12L)
                .extension("docx")
                .bytes("Hello, World!")
                .build();


        //when
        when(requestToFileDTOConverter.convert(multipartFile)).thenReturn(fileDTO);
        when(fileService.update(eq(fileId), any(FileDTO.class))).thenReturn(fileDTO);
        BaseResponse response = fileManager.update(fileId, multipartFile);

        //then
        assertEquals(SuccessResponse.class, response.getClass());
        assertEquals(SuccessMessage.FILE_UPDATE_SUCCESS.getValue(), response.getMessage());
        assertEquals(fileDTO, ((SuccessResponse) response).getData().get("data"));
    }

    @Test
    public void should_return_bad_request_response_at_update() {
        //given
        int fileId = 1;
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());
        FileDTO fileDTO = FileDTO.builder()
                .id(fileId)
                .name("test.txt")
                .size(12L)
                .extension("txt")
                .bytes("Hello, World!")
                .build();


        //when
        when(requestToFileDTOConverter.convert(multipartFile)).thenReturn(fileDTO);
        doThrow(new BadRequestException(ErrorMessage.INVALID_FILE_EXTENSION.getValue())).when(fileSaveRequestValidator).validate(fileDTO);
        BaseResponse response = fileManager.update(fileId, multipartFile);

        //then
        assertEquals(BadRequestResponse.class, response.getClass());
        assertEquals(ErrorMessage.INVALID_FILE_EXTENSION.getValue(), response.getMessage());
    }

    @Test
    public void should_return_not_found_response_at_update() {
        //given
        int fileId = 1;
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.docx", "text/plain", "Hello, World!".getBytes());
        FileDTO fileDTO = FileDTO.builder()
                .id(fileId)
                .name("test.docx")
                .size(12L)
                .extension("docx")
                .bytes("Hello, World!")
                .build();


        //when
        when(requestToFileDTOConverter.convert(multipartFile)).thenReturn(fileDTO);
        doThrow(new ObjectNotFoundException(fileId, ErrorMessage.FILE_NOT_FOUND.getValue())).when(fileService).update(eq(fileId), any(FileDTO.class));
        BaseResponse response = fileManager.update(fileId, multipartFile);

        //then
        assertEquals(BadRequestResponse.class, response.getClass());
    }

    @Test
    public void should_delete() {
        //given
        int fileId = 1;

        //when
        BaseResponse response = fileManager.delete(fileId);

        //then
        assertEquals(SuccessResponse.class, response.getClass());
        assertEquals(SuccessMessage.FILE_DELETE_SUCCESS.getValue(), response.getMessage());
        assertEquals(fileId, ((SuccessResponse) response).getData().get("data"));
    }

}