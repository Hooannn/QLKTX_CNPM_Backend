package com.ht.qlktx.modules.student;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Student;
import com.ht.qlktx.modules.student.dtos.CreateStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student create(CreateStudentDto createStudentDto) {
        if (studentRepository.existsById(createStudentDto.getId()))
            throw new HttpException("Student already exists", HttpStatus.BAD_REQUEST);

        var student = Student.builder()
                .id(createStudentDto.getId())
                .firstName(createStudentDto.getFirstName())
                .lastName(createStudentDto.getLastName())
                .dateOfBirth(createStudentDto.getDateOfBirth())
                .phone(createStudentDto.getPhone())
                .sex(createStudentDto.getSex())
                .email(createStudentDto.getEmail())
                .address(createStudentDto.getAddress())
                .build();

        return studentRepository.save(student);
    }

    public Student findById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new HttpException("Student not found", HttpStatus.NOT_FOUND));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
