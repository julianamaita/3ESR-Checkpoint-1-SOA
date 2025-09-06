package br.com.fiap.autoescola.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="students")
public class Student {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank private String name;
  @Email @NotBlank @Column(unique=true) private String email;
  @NotBlank private String phone;
  @NotBlank @Column(unique=true) private String cpf;

  @Embedded
  private Address address;

  @Column(nullable=false)
  private boolean active = true;

  public Student() {}
  public Student(String name, String email, String phone, String cpf, Address address) {
    this.name = name; this.email = email; this.phone = phone; this.cpf = cpf; this.address = address;
  }

  // getters and setters
  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public String getCpf() { return cpf; }
  public void setCpf(String cpf) { this.cpf = cpf; }
  public Address getAddress() { return address; }
  public void setAddress(Address address) { this.address = address; }
  public boolean isActive() { return active; }
  public void setActive(boolean active) { this.active = active; }
}
