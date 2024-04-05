package com.ht.qlktx.modules.student;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Account;
import com.ht.qlktx.entities.Staff;
import com.ht.qlktx.entities.Student;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.account.AccountRepository;
import com.ht.qlktx.modules.account.RoleRepository;
import com.ht.qlktx.modules.student.dtos.CreateStudentDto;
import com.ht.qlktx.modules.student.dtos.UpdateProfileDto;
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
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
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
        if (studentRepository.existsById(createStudentDto.getId()) || accountRepository.existsByEmail(createStudentDto.getEmail())) {
            throw new HttpException("Mã người dùng hoặc email đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        var role = roleRepository.findByRole(Role.STUDENT.toString()).orElseThrow(
                () -> new HttpException("Không tồn tại quyền sinh viên", HttpStatus.BAD_REQUEST)
        );

        var account = Account.builder()
                .username(createStudentDto.getId())
                .password(passwordEncoder.encode(createStudentDto.getPassword()))
                .role(role)
                .email(createStudentDto.getEmail())
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
                .build();

        return studentRepository.save(student);
    }

    public Student update(String studentId, UpdateStudentDto updateStudentDto) {
        var student = findById(studentId);

        Optional.ofNullable(updateStudentDto.getPassword()).ifPresent(password -> {
            student.getAccount().setPassword(passwordEncoder.encode(password));
        });

        Optional.ofNullable(updateStudentDto.getEmail()).ifPresent(email -> {
            if (!email.equals(student.getAccount().getEmail()) && accountRepository.existsByEmail(email)) {
                throw new HttpException("Email đã tồn tại", HttpStatus.BAD_REQUEST);
            }
            student.getAccount().setEmail(email);
        });

        Optional.ofNullable(updateStudentDto.getFirstName()).ifPresent(student::setFirstName);
        Optional.ofNullable(updateStudentDto.getLastName()).ifPresent(student::setLastName);
        Optional.ofNullable(updateStudentDto.getSex()).ifPresent(student::setSex);
        Optional.ofNullable(updateStudentDto.getDateOfBirth()).ifPresent(student::setDateOfBirth);
        Optional.ofNullable(updateStudentDto.getAddress()).ifPresent(student::setAddress);
        Optional.ofNullable(updateStudentDto.getPhone()).ifPresent(student::setPhone);

        return studentRepository.save(student);
    }

    public Student update(String studentId, UpdateProfileDto updateProfileDto) {
        var student = findById(studentId);

        Optional.ofNullable(updateProfileDto.getFirstName()).ifPresent(student::setFirstName);
        Optional.ofNullable(updateProfileDto.getLastName()).ifPresent(student::setLastName);
        Optional.ofNullable(updateProfileDto.getSex()).ifPresent(student::setSex);
        Optional.ofNullable(updateProfileDto.getDateOfBirth()).ifPresent(student::setDateOfBirth);
        Optional.ofNullable(updateProfileDto.getAddress()).ifPresent(student::setAddress);
        Optional.ofNullable(updateProfileDto.getPhone()).ifPresent(student::setPhone);

        return studentRepository.save(student);
    }

    public void delete(String studentId) {
        var student = findById(studentId);
        student.setDeleted(true);
        studentRepository.save(student);
    }
}
