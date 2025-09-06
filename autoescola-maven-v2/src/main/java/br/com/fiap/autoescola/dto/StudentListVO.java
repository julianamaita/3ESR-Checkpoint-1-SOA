package br.com.fiap.autoescola.dto;

import br.com.fiap.autoescola.domain.Student;

public class StudentListVO {
  public Long id;
  public String name;
  public String email;
  public String cpf;

  public static StudentListVO of(Student s){
    StudentListVO vo = new StudentListVO();
    vo.id = s.getId();
    vo.name = s.getName();
    vo.email = s.getEmail();
    vo.cpf = s.getCpf();
    return vo;
  }
}
