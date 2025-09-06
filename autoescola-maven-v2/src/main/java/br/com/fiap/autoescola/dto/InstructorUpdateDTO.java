package br.com.fiap.autoescola.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InstructorUpdateDTO {
  @NotBlank public String name;
  public String phone; // pode ser nulo para manter
  @NotNull public AddressDTO address;
}
