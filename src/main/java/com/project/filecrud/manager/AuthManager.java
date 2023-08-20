package com.project.filecrud.manager;

import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.SuccessMessage;
import com.project.filecrud.model.request.AuthRequest;
import com.project.filecrud.model.response.BaseResponse;
import com.project.filecrud.model.response.file.BadRequestResponse;
import com.project.filecrud.model.response.file.SuccessResponse;
import com.project.filecrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthManager {
    private final AuthService authService;

    public BaseResponse register(AuthRequest authRequest){
        try{
            authService.register(authRequest);
        }catch (Exception e){
            return new BadRequestResponse(e.getMessage());
        }

        return new SuccessResponse(SuccessMessage.USER_REGISTERED.getValue());
    }

    public BaseResponse login(AuthRequest authRequest){
        String token;
        try{
            token = authService.login(authRequest);
        }catch (BadCredentialsException e){
            return new BadRequestResponse(ErrorMessage.INVALID_USERNAME_PASSWORD.getValue());
        }catch (Exception e){
            return new BadRequestResponse(e.getMessage());
        }
        return new SuccessResponse(SuccessMessage.USER_LOGGED_IN.getValue(), "token" ,token);
    }
}
