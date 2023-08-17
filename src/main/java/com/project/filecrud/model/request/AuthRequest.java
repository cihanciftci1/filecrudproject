package com.project.filecrud.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
