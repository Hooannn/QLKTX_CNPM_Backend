package com.ht.qlktx.modules.student;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Account;
import com.ht.qlktx.entities.Staff;
import com.ht.qlktx.entities.Student;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.student.dtos.CreateStudentDto;
import com.ht.qlktx.modules.student.dtos.UpdateStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Student> findAll() {
        return studentRepository.findAllByDeletedIsFalse();
    }

    public List<Student> lookupByIdOrName(String keyword) {
        return studentRepository.lookupByIdOrName(keyword);
    }

    public Student findById(String studentId) {
        return studentRepository.findByIdAndDeletedIsFalse(studentId).orElseThrow(
                () -> new HttpException("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST)
        );
    }

    @Transactional
    public Student create(CreateStudentDto createStudentDto) {
        if (studentRepository.existsById(createStudentDto.getId()) || studentRepository.existsByEmail(createStudentDto.getEmail())) {
            throw new HttpException("Mã người dùng hoặc email đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        var account = Account.builder()
                .username(createStudentDto.getId())
                .password(passwordEncoder.encode(createStudentDto.getPassword()))
                .role(Role.STUDENT)
                .build();

        var student = Student.builder()
                .id(createStudentDto.getId())
                .account(account)
                .firstName(createStudentDto.getFirstName())
                .lastName(createStudentDto.getLastName())
                .sex(createStudentDto.getSex())
                .dateOfBirth(createStudentDto.getDateOfBirth())
                .address(createStudentDto.getAddress())
                .phone(createStudentDto.getPhone())
                .email(createStudentDto.getEmail())
                .build();

        return studentRepository.save(student);
    }

    public Student update(String studentId, UpdateStudentDto updateStudentDto) {
        var student = findById(studentId);

        Optional.ofNullable(updateStudentDto.getPassword()).ifPresent(password -> {
            student.getAccount().setPassword(passwordEncoder.encode(password));
        });

        Optional.ofNullable(updateStudentDto.getFirstName()).ifPresent(student::setFirstName);
        Optional.ofNullable(updateStudentDto.getLastName()).ifPresent(student::setLastName);
        Optional.ofNullable(updateStudentDto.getSex()).ifPresent(student::setSex);
        Optional.ofNullable(updateStudentDto.getDateOfBirth()).ifPresent(student::setDateOfBirth);
        Optional.ofNullable(updateStudentDto.getAddress()).ifPresent(student::setAddress);
        Optional.ofNullable(updateStudentDto.getPhone()).ifPresent(student::setPhone);
        Optional.ofNullable(updateStudentDto.getEmail()).ifPresent(email -> {
            if (!email.equals(student.getEmail()) && studentRepository.existsByEmail(email)) {
                throw new HttpException("Email đã tồn tại", HttpStatus.BAD_REQUEST);
            }
            student.setEmail(email);
        });

        return studentRepository.save(student);
    }

    public void delete(String studentId) {
        var student = findById(studentId);
        student.setDeleted(true);
        studentRepository.save(student);
    }
}
