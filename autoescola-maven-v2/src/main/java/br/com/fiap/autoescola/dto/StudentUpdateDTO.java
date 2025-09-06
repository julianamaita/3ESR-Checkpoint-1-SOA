package br.com.fiap.autoescola.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentUpdateDTO {
  @NotBlank public String name;
  @NotBlank public String phone;
  @NotNull public AddressDTO address;
}
