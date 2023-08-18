package com.project.filecrud.service;

import com.project.filecrud.entity.Account;
import com.project.filecrud.enums.Role;
import com.project.filecrud.model.exception.BadRequestException;
import com.project.filecrud.model.request.AuthRequest;
import com.project.filecrud.repository.AccountRepository;
import com.project.filecrud.service.security.JWTGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTGenerator jwtGenerator;

    private AuthService authService;

    @BeforeEach
    public void setup(){
        this.authService = new AuthService(accountRepository, passwordEncoder, authenticationManager, jwtGenerator);
    }
    @Test
    public void register_should_create_account_when_registration_is_successful() {
        //given
        AuthRequest authRequest = new AuthRequest("user", "password");
        Account account = Account.builder()
                .username(authRequest.getUsername())
                .password("encoded_password")
                .role(Role.USER)
                .build();

        //when
        when(accountRepository.existsByUsername(authRequest.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(authRequest.getPassword())).thenReturn("encoded_password");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        //then
        assertDoesNotThrow(() -> authService.register(authRequest));
    }

    @Test
    public void register_should_throw_bad_request_exception_when_username_is_taken() {
        //given
        AuthRequest authRequest = new AuthRequest("user", "password");

        //when
        when(accountRepository.existsByUsername(anyString())).thenReturn(true);
        //then
        assertThrows(BadRequestException.class, () -> authService.register(authRequest));
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void login_should_generate_token_when_login_is_successful() {
        //given
        AuthRequest authRequest = new AuthRequest("user", "password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        String expectedToken = "expected_token";

        //when
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtGenerator.generateToken(authentication)).thenReturn(expectedToken);

        String token = authService.login(authRequest);

        //then
        assertEquals(expectedToken, token);
        verify(authenticationManager).authenticate(any(Authentication.class));
    }

}
