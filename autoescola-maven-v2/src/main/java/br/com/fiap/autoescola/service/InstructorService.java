package br.com.fiap.autoescola.service;

import br.com.fiap.autoescola.domain.Address;
import br.com.fiap.autoescola.domain.Instructor;
import br.com.fiap.autoescola.dto.*;
import br.com.fiap.autoescola.repo.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorService {
  private final InstructorRepository repo;
  public InstructorService(InstructorRepository repo){ this.repo = repo; }

  @Transactional
  public Long create(InstructorCreateDTO dto){
    Address a = new Address(dto.address.street, dto.address.number, dto.address.complement,
            dto.address.district, dto.address.city, dto.address.state, dto.address.zip);
    Instructor i = new Instructor(dto.name, dto.email, null, dto.cnh, dto.specialty, a);
    return repo.save(i).getId();
  }

  public Page<InstructorListVO> list(int page, int size){
    return repo.findByActiveTrueOrderByNameAsc(PageRequest.of(page, size))
            .map(InstructorListVO::of);
  }

  @Transactional
  public void update(Long id, InstructorUpdateDTO dto){
    Instructor i = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado"));
    i.setName(dto.name);
    if (dto.phone != null) i.setPhone(dto.phone);
    i.setAddress(new Address(dto.address.street, dto.address.number, dto.address.complement,
            dto.address.district, dto.address.city, dto.address.state, dto.address.zip));
    repo.save(i);
  }

  @Transactional
  public void inactivate(Long id){
    Instructor i = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado"));
    i.setActive(false);
    repo.save(i);
  }

  public Instructor getActive(Long id){
    Instructor i = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado"));
    if (!i.isActive()) throw new IllegalStateException("Instrutor inativo");
    return i;
  }
}
