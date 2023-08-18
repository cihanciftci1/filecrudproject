package com.project.filecrud.controller;

import com.project.filecrud.dto.FileDTO;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.manager.FileManager;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class FileControllerTest {

    @Mock
    private FileManager fileManager;

    private FileController fileController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        this.fileController = new FileController(fileManager);
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    public void should_save() throws Exception {
        //given
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test.docx",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        FileDTO fileDTO = FileDTO.builder()
                .name("test")
                .extension("docx")
                .bytes(Arrays.toString(mockFile.getBytes()))
                .size(mockFile.getSize())
                .build();

        SuccessResponse expectedResponse = new SuccessResponse(SuccessMessage.FILE_SAVE_SUCCESS.getValue(), fileDTO);

        //when
        when(fileManager.save(any())).thenReturn(expectedResponse);
        mockMvc.perform(multipart("/api/v1/file")
                        .file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));

        //them
        verify(fileManager, times(1)).save(any());
        verifyNoMoreInteractions(fileManager);
    }
    @Test
    void should_retrieve() throws Exception {
        //given
        BaseResponse expectedResponse = new BaseResponse(SuccessMessage.FILE_RETRIEVE_SUCCESS.getValue());

        //when
        when(fileManager.retrieve(anyInt())).thenReturn(expectedResponse);
        mockMvc.perform(get("/api/v1/file/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));

        //them
        verify(fileManager, times(1)).retrieve(anyInt());
        verifyNoMoreInteractions(fileManager);
    }

    @Test
    public void should_update() throws Exception {
        //given
        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test.docx",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        FileDTO fileDTO = FileDTO.builder()
                .name("test")
                .extension("docx")
                .bytes(Arrays.toString(mockFile.getBytes()))
                .size(mockFile.getSize())
                .build();

        SuccessResponse expectedResponse = new SuccessResponse(SuccessMessage.FILE_UPDATE_SUCCESS.getValue(), fileDTO);

        //when
        when(fileManager.update(anyInt(), any())).thenReturn(expectedResponse);
        mockMvc.perform(multipart("/api/v1/file/{id}", 1)
                        .file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));

        //then
        verify(fileManager, times(1)).update(anyInt(), any());
        verifyNoMoreInteractions(fileManager);
    }

    @Test
    public void should_delete() throws Exception {
        //given
        BaseResponse expectedResponse = new BaseResponse(SuccessMessage.FILE_DELETE_SUCCESS.getValue());

        //when
        when(fileManager.delete(anyInt())).thenReturn(expectedResponse);
        mockMvc.perform(delete("/api/v1/file/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));

        //then
        verify(fileManager, times(1)).delete(anyInt());
        verifyNoMoreInteractions(fileManager);
    }
}
