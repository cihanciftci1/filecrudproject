package com.project.filecrud.service;

import com.project.filecrud.entity.Account;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.enums.Role;
import com.project.filecrud.model.exception.BadRequestException;
import com.project.filecrud.model.request.AuthRequest;
import com.project.filecrud.repository.AccountRepository;
import com.project.filecrud.service.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;


    public void register(AuthRequest authRequest){
        log.info("Auth Service register is starting with | : {}", authRequest.getUsername());
        if (accountRepository.existsByUsername(authRequest.getUsername())) throw new BadRequestException(ErrorMessage.USERNAME_TAKEN.getValue());

        Account account = Account.builder()
                .username(authRequest.getUsername())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Role.USER)
                .build();

        accountRepository.save(account);

        log.info("Auth Service register is finished for | : {}", authRequest.getUsername());
    }

    public String login(AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtGenerator.generateToken(authentication);
    }
}
