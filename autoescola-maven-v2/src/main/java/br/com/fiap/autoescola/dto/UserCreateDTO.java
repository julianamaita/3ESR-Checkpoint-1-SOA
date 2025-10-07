package br.com.fiap.autoescola.dto;

import br.com.fiap.autoescola.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
        @NotBlank @Size(min = 3, max = 120) String username,
        @NotBlank @Size(min = 6, max = 120) String password,
        Role role
) {}
