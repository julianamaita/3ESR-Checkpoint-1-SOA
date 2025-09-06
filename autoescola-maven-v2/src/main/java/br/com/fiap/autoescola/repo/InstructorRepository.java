package br.com.fiap.autoescola.repo;

import br.com.fiap.autoescola.domain.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
  Page<Instructor> findByActiveTrueOrderByNameAsc(Pageable pageable);
}
