package gov.edu.anm.presenter.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.edu.anm.presenter.entities.Student;
import gov.edu.anm.presenter.repositories.StudentRepository;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentRepository.findById(id).get());
    }

}
