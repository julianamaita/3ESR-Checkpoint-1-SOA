package br.com.fiap.autoescola.dto;

import br.com.fiap.autoescola.domain.Instructor;

public class InstructorListVO {
  public Long id;
  public String name;
  public String email;
  public String cnh;
  public String specialty;

  public static InstructorListVO of(Instructor i){
    InstructorListVO vo = new InstructorListVO();
    vo.id = i.getId();
    vo.name = i.getName();
    vo.email = i.getEmail();
    vo.cnh = i.getCnh();
    vo.specialty = i.getSpecialty().name();
    return vo;
  }
}
