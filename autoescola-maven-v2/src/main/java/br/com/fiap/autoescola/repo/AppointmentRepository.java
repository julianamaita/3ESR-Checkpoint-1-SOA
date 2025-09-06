package br.com.fiap.autoescola.repo;

import br.com.fiap.autoescola.domain.Appointment;
import br.com.fiap.autoescola.domain.Instructor;
import br.com.fiap.autoescola.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  boolean existsByInstructorAndStartTime(Instructor instructor, LocalDateTime start);
  long countByStudentAndStartTimeBetween(Student student, LocalDateTime start, LocalDateTime end);
  List<Appointment> findByStudent(Student student);
}
