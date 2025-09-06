package br.com.fiap.autoescola.dto;

import br.com.fiap.autoescola.domain.CancellationReason;
import jakarta.validation.constraints.NotNull;

public class AppointmentCancelDTO {
  @NotNull public CancellationReason reason;
}
