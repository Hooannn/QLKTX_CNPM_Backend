package com.ht.qlktx.modules.student;

import com.ht.qlktx.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllByDeletedIsFalse();

    @Query("SELECT u FROM Student u WHERE u.deleted = false and (u.id LIKE %:keyword% OR concat(u.firstName, ' ', u.lastName) LIKE %:keyword%)")
    List<Student> lookupByIdOrName(String keyword);

    Optional<Student> findByIdAndDeletedIsFalse(String studentId);

    Long countByDeletedIsFalse();
}
