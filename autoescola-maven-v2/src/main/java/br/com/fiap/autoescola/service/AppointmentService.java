package br.com.fiap.autoescola.service;

import br.com.fiap.autoescola.domain.*;
import br.com.fiap.autoescola.dto.AppointmentCancelDTO;
import br.com.fiap.autoescola.dto.AppointmentCreateDTO;
import br.com.fiap.autoescola.repo.AppointmentRepository;
import br.com.fiap.autoescola.repo.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AppointmentService {
  private final AppointmentRepository repo;
  private final StudentService students;
  private final InstructorService instructors;
  private final InstructorRepository instructorRepo;
  private final Random random = new Random();

  public AppointmentService(AppointmentRepository repo, StudentService students, InstructorService instructors, InstructorRepository instructorRepo){
    this.repo = repo; this.students = students; this.instructors = instructors; this.instructorRepo = instructorRepo;
  }

  private void validateBusinessRules(Student s, Instructor i, LocalDateTime start){
    // antecedência mínima de 30 minutos
    if (start.isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(30))) {
      throw new IllegalArgumentException("Agendamento deve ter antecedência mínima de 30 minutos.");
    }
    // horário de funcionamento (segunda a sábado, 06:00-21:00)
    DayOfWeek dow = start.getDayOfWeek();
    if (dow == DayOfWeek.SUNDAY) throw new IllegalArgumentException("Domingo não é dia de funcionamento.");
    LocalTime lt = start.toLocalTime();
    if (lt.isBefore(LocalTime.of(6,0)) || !lt.isBefore(LocalTime.of(21,0))) {
      throw new IllegalArgumentException("Horário permitido: 06:00 às 21:00.");
    }
    // máximo 2 instruções por dia para o aluno
    LocalDate day = start.toLocalDate();
    long count = repo.countByStudentAndStartTimeBetween(s,
            day.atStartOfDay(), day.plusDays(1).atStartOfDay());
    if (count >= 2) throw new IllegalArgumentException("Aluno já possui 2 instruções neste dia.");
    // conflito de horário do instrutor
    if (repo.existsByInstructorAndStartTime(i, start)) {
      throw new IllegalArgumentException("Instrutor já possui instrução neste horário.");
    }
  }

  @Transactional
  public Long schedule(AppointmentCreateDTO dto){
    Student s = students.getActive(dto.studentId);
    Instructor i;
    if (dto.instructorId != null) {
      i = instructors.getActive(dto.instructorId);
    } else {
      // escolher aleatoriamente um instrutor ativo disponível
      List<Instructor> all = instructorRepo.findAll().stream().filter(Instructor::isActive).toList();
      if (all.isEmpty()) throw new IllegalStateException("Nenhum instrutor ativo disponível.");
      // pick a random available one (no conflicting appointment at start)
      Optional<Instructor> any = all.stream().filter(inst -> !repo.existsByInstructorAndStartTime(inst, dto.startTime)).findAny();
      if (any.isEmpty()) throw new IllegalStateException("Nenhum instrutor disponível neste horário.");
      i = any.get();
    }
    LocalDateTime start = dto.startTime;
    LocalDateTime end = start.plusHours(1);
    validateBusinessRules(s, i, start);
    Appointment ap = new Appointment(s, i, start, end);
    return repo.save(ap).getId();
  }

  @Transactional
  public void cancel(Long id, AppointmentCancelDTO dto){
    Appointment ap = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Instrução não encontrada"));
    // regra: cancelar com 24h de antecedência
    if (ap.getStartTime().isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(24))) {
      throw new IllegalArgumentException("Cancelamento permitido apenas com 24h de antecedência.");
    }
    ap.setStatus("CANCELED");
    ap.setCancellationReason(dto.reason.name());
    repo.save(ap);
  }
}
