package br.com.fiap.autoescola.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="instructors")
public class Instructor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank private String name;
  @Email @NotBlank @Column(unique = true) private String email;
  private String phone; // oculto inicialmente
  @NotBlank @Column(unique = true) private String cnh;
  @Enumerated(EnumType.STRING) @Column(nullable=false)
  private Specialty specialty;

  @Embedded
  private Address address;

  @Column(nullable=false)
  private boolean active = true;

  public Instructor() {}
  public Instructor(String name, String email, String phone, String cnh, Specialty specialty, Address address) {
    this.name = name; this.email = email; this.phone = phone; this.cnh = cnh; this.specialty = specialty; this.address = address;
  }

  // getters and setters
  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public String getCnh() { return cnh; }
  public void setCnh(String cnh) { this.cnh = cnh; }
  public Specialty getSpecialty() { return specialty; }
  public void setSpecialty(Specialty specialty) { this.specialty = specialty; }
  public Address getAddress() { return address; }
  public void setAddress(Address address) { this.address = address; }
  public boolean isActive() { return active; }
  public void setActive(boolean active) { this.active = active; }
}
