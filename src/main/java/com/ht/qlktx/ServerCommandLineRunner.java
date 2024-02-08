package com.ht.qlktx;

import com.ht.qlktx.entities.User;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.enums.Sex;
import com.ht.qlktx.modules.users.UserRepository;
import com.ht.qlktx.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        List<User> students = Helper.createSeedStudents();
        List<User> staffs = Helper.createSeedStaffs();

        if (userRepository.count() == 0 && students != null && staffs != null) {
            staffs.forEach(staff -> staff.setPassword(passwordEncoder.encode(staff.getId())));
            students.forEach(student -> student.setPassword(passwordEncoder.encode(student.getId())));
            userRepository.saveAll(students);
            userRepository.saveAll(staffs);
        }
        //Create default admin account
        String defaultAdminUsername = "admin001";
        String defaultAdminPassword = "123456";
        if (userRepository.existsById(defaultAdminUsername)) {
            return;
        }
        User user = User.builder()
                .id(defaultAdminUsername)
                .password(passwordEncoder.encode(defaultAdminPassword))
                .sex(Sex.MALE)
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@qlktx.ptithcm.edu.vn")
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
}