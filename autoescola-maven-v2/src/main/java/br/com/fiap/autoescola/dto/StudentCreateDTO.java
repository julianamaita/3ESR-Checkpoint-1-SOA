package br.com.fiap.autoescola.dto;

import jakarta.validation.constraints.*;

public class StudentCreateDTO {
  @NotBlank public String name;
  @Email @NotBlank public String email;
  @NotBlank public String phone;
  @NotBlank public String cpf;
  @NotNull public AddressDTO address;
}
