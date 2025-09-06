package br.com.fiap.autoescola.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentCreateDTO {
  @NotNull public Long studentId;
  public Long instructorId; // opcional
  @NotNull public LocalDateTime startTime; // duração fixa de 1h
}
