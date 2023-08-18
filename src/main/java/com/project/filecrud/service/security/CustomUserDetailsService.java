package com.project.filecrud.service.security;

import com.project.filecrud.entity.Account;
import com.project.filecrud.enums.ErrorMessage;
import com.project.filecrud.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.USERNAME_NOT_FOUND.getValue()));
        return new User(account.getUsername(), account.getPassword(), Set.of(new SimpleGrantedAuthority(account.getRole().toString())));
    }
}
