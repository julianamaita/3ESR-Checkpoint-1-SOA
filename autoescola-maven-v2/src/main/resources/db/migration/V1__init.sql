CREATE TABLE instructors (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  phone VARCHAR(30),
  cnh VARCHAR(30) NOT NULL UNIQUE,
  specialty VARCHAR(20) NOT NULL,
  street VARCHAR(120) NOT NULL,
  number VARCHAR(20),
  complement VARCHAR(60),
  district VARCHAR(80) NOT NULL,
  city VARCHAR(80) NOT NULL,
  state CHAR(2) NOT NULL,
  zip VARCHAR(12) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE INDEX idx_instructors_name ON instructors(name);

CREATE TABLE students (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  phone VARCHAR(30) NOT NULL,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  street VARCHAR(120) NOT NULL,
  number VARCHAR(20),
  complement VARCHAR(60),
  district VARCHAR(80) NOT NULL,
  city VARCHAR(80) NOT NULL,
  state CHAR(2) NOT NULL,
  zip VARCHAR(12) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE INDEX idx_students_name ON students(name);

CREATE TABLE appointments (
  id BIGSERIAL PRIMARY KEY,
  student_id BIGINT NOT NULL REFERENCES students(id),
  instructor_id BIGINT NOT NULL REFERENCES instructors(id),
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL,
  status VARCHAR(20) NOT NULL,
  cancellation_reason VARCHAR(30)
);

CREATE INDEX idx_appointments_start ON appointments(start_time);
