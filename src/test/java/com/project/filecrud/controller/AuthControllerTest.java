package com.project.filecrud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.manager.AuthManager;
import com.project.filecrud.model.request.AuthRequest;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {


    @Mock
    private AuthManager authManager;

    private AuthController authController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        this.authController = new AuthController(authManager);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void should_register_success() throws Exception {
        //given
        AuthRequest request = new AuthRequest("user", "password");
        String message = SuccessMessage.USER_REGISTERED.getValue();
        BaseResponse expectedResponse = new SuccessResponse(message);

        //when
        Mockito.when(authManager.register(request)).thenReturn(expectedResponse);

        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));

        //then
        Mockito.verify(authManager, Mockito.times(1)).register(request);
        Mockito.verifyNoMoreInteractions(authManager);
    }


    @Test
    public void should_login_success() throws Exception {
        //given
        AuthRequest request = new AuthRequest("user", "password");
        String token = "generatedToken";
        BaseResponse expectedResponse = new SuccessResponse(SuccessMessage.USER_LOGGED_IN.getValue(), "token", token);

        //when
        Mockito.when(authManager.login(request)).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()))
                .andExpect(jsonPath("$.data.token").value(token));

        //then
        Mockito.verify(authManager, Mockito.times(1)).login(request);
        Mockito.verifyNoMoreInteractions(authManager);
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}