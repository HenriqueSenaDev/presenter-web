package gov.edu.anm.presenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.edu.anm.presenter.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
