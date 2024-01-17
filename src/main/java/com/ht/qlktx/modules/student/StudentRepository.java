package com.ht.qlktx.modules.student;

import com.ht.qlktx.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
