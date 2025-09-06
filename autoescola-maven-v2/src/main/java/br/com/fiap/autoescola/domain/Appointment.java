package br.com.fiap.autoescola.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="appointments")
public class Appointment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) private Student student;
  @ManyToOne(optional=false) private Instructor instructor;

  @Column(nullable=false) private LocalDateTime startTime;
  @Column(nullable=false) private LocalDateTime endTime;

  @Column(nullable=false) private String status; // SCHEDULED, CANCELED
  private String cancellationReason; // enum name

  public Appointment(){}
  public Appointment(Student s, Instructor i, LocalDateTime start, LocalDateTime end) {
    this.student = s; this.instructor = i; this.startTime = start; this.endTime = end; this.status = "SCHEDULED";
  }

  // getters and setters
  public Long getId() { return id; }
  public Student getStudent() { return student; }
  public void setStudent(Student student) { this.student = student; }
  public Instructor getInstructor() { return instructor; }
  public void setInstructor(Instructor instructor) { this.instructor = instructor; }
  public LocalDateTime getStartTime() { return startTime; }
  public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
  public LocalDateTime getEndTime() { return endTime; }
  public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public String getCancellationReason() { return cancellationReason; }
  public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }
}
