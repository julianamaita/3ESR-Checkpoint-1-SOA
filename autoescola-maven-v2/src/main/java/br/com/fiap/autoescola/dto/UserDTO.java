package br.com.fiap.autoescola.dto;

import br.com.fiap.autoescola.domain.Role;
import java.time.Instant;

public record UserDTO(Long id, String username, Role role, boolean enabled, Instant createdAt) {}
