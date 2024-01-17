package com.ht.qlktx.modules.student;

import com.ht.qlktx.entities.Student;
import com.ht.qlktx.modules.student.dtos.CreateStudentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody CreateStudentDto createStudentDto) {
        var student = studentService.create(createStudentDto);
        return ResponseEntity.created(null).body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        var students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable String id) {
        var student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }
}
