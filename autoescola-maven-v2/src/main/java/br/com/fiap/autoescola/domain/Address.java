package br.com.fiap.autoescola.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Address {
  @NotBlank private String street;
  private String number;
  private String complement;
  @NotBlank private String district;
  @NotBlank private String city;
  @NotBlank private String state;
  @NotBlank private String zip;

  public Address() {}
  public Address(String street, String number, String complement, String district, String city, String state, String zip) {
    this.street = street; this.number = number; this.complement = complement;
    this.district = district; this.city = city; this.state = state; this.zip = zip;
  }
  // getters and setters
  public String getStreet() { return street; }
  public void setStreet(String street) { this.street = street; }
  public String getNumber() { return number; }
  public void setNumber(String number) { this.number = number; }
  public String getComplement() { return complement; }
  public void setComplement(String complement) { this.complement = complement; }
  public String getDistrict() { return district; }
  public void setDistrict(String district) { this.district = district; }
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  public String getState() { return state; }
  public void setState(String state) { this.state = state; }
  public String getZip() { return zip; }
  public void setZip(String zip) { this.zip = zip; }
}
