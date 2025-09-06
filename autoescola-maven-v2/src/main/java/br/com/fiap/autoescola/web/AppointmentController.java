package br.com.fiap.autoescola.web;

import br.com.fiap.autoescola.dto.AppointmentCancelDTO;
import br.com.fiap.autoescola.dto.AppointmentCreateDTO;
import br.com.fiap.autoescola.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
  private final AppointmentService service;
  public AppointmentController(AppointmentService service){ this.service = service; }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long schedule(@Valid @RequestBody AppointmentCreateDTO dto){
    return service.schedule(dto);
  }

  @PostMapping("/{id}/cancel")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void cancel(@PathVariable Long id, @Valid @RequestBody AppointmentCancelDTO dto){
    service.cancel(id, dto);
  }
}
