package br.com.fiap.autoescola.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressDTO {
  @NotBlank public String street;
  public String number;
  public String complement;
  @NotBlank public String district;
  @NotBlank public String city;
  @NotBlank public String state;
  @NotBlank public String zip;
}
