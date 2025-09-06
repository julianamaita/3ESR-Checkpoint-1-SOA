package br.com.fiap.autoescola.repo;

import br.com.fiap.autoescola.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Page<Student> findByActiveTrueOrderByNameAsc(Pageable pageable);
}
