package br.com.fiap.autoescola.web;

import br.com.fiap.autoescola.dto.*;
import br.com.fiap.autoescola.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
  private final InstructorService service;
  public InstructorController(InstructorService service){ this.service = service; }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long create(@Valid @RequestBody InstructorCreateDTO dto) {
    return service.create(dto);
  }

  @GetMapping
  public Page<InstructorListVO> list(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size){
    return service.list(page, size);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable Long id, @Valid @RequestBody InstructorUpdateDTO dto){
    service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inactivate(@PathVariable Long id){
    service.inactivate(id);
  }
}
