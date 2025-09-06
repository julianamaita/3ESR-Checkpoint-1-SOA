package br.com.fiap.autoescola.service;

import br.com.fiap.autoescola.domain.Address;
import br.com.fiap.autoescola.domain.Student;
import br.com.fiap.autoescola.dto.*;
import br.com.fiap.autoescola.repo.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
  private final StudentRepository repo;
  public StudentService(StudentRepository repo){ this.repo = repo; }

  @Transactional
  public Long create(StudentCreateDTO dto){
    Address a = new Address(dto.address.street, dto.address.number, dto.address.complement,
            dto.address.district, dto.address.city, dto.address.state, dto.address.zip);
    Student s = new Student(dto.name, dto.email, dto.phone, dto.cpf, a);
    return repo.save(s).getId();
  }

  public Page<StudentListVO> list(int page, int size){
    return repo.findByActiveTrueOrderByNameAsc(PageRequest.of(page, size))
            .map(StudentListVO::of);
  }

  @Transactional
  public void update(Long id, StudentUpdateDTO dto){
    Student s = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
    s.setName(dto.name);
    s.setPhone(dto.phone);
    s.setAddress(new Address(dto.address.street, dto.address.number, dto.address.complement,
            dto.address.district, dto.address.city, dto.address.state, dto.address.zip));
    repo.save(s);
  }

  @Transactional
  public void inactivate(Long id){
    Student s = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
    s.setActive(false);
    repo.save(s);
  }

  public Student getActive(Long id){
    Student s = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
    if (!s.isActive()) throw new IllegalStateException("Aluno inativo");
    return s;
  }
}
