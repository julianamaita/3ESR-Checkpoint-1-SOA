package br.com.fiap.autoescola.dto;

import br.com.fiap.autoescola.domain.Specialty;
import jakarta.validation.constraints.*;

public class InstructorCreateDTO {
  @NotBlank public String name;
  @Email @NotBlank public String email;
  // phone oculto inicialmente - opcional no cadastro inicial
  @NotBlank public String cnh;
  @NotNull public Specialty specialty;
  @NotNull public AddressDTO address;
}
