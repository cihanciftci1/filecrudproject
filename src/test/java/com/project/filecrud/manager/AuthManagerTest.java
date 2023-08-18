package com.project.filecrud.manager;

import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.request.AuthRequest;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.BadRequestResponse;
import com.project.filecrud.model.response.file.SuccessResponse;
import com.project.filecrud.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthManagerTest {

    @Mock
    private AuthService authService;

    private AuthManager authManager;

    @BeforeEach
    public void setup(){
        this.authManager = new AuthManager(authService);
    }

    @Test
    public void register_should_return_success_response_when_registration_is_successful() {
        //given
        AuthRequest authRequest = new AuthRequest("user", "password");
        BaseResponse expectedResponse = new SuccessResponse(SuccessMessage.USER_REGISTERED.getValue());

        //when
        BaseResponse actualResponse = authManager.register(authRequest);

        //then
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void register_should_return_bad_request_response_when_registration_fails() {
        //given
        AuthRequest authRequest = new AuthRequest("user", "password");
        String errorMessage = ErrorMessage.USERNAME_TAKEN.getValue();
        BaseResponse expectedResponse = new BadRequestResponse(errorMessage);

        //when
        doThrow(new RuntimeException(errorMessage)).when(authService).register(authRequest);
        BaseResponse actualResponse = authManager.register(authRequest);

        //then
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void login_should_return_success_response_with_token_when_login_is_successful() {
        //given
        AuthRequest authRequest = new AuthRequest("user", "password");
        String token = "token";
        BaseResponse expectedResponse = new SuccessResponse(SuccessMessage.USER_LOGGED_IN.getValue(), "token", token);

        //when
        when(authService.login(authRequest)).thenReturn(token);
        BaseResponse actualResponse = authManager.login(authRequest);

        //then
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

}
