package com.ht.qlktx.modules.users;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.User;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.users.dtos.CreateUserDto;
import com.ht.qlktx.modules.users.dtos.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User create(CreateUserDto createUserDto) {
        if (userRepository.existsById(createUserDto.getId()) || userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new HttpException("Mã người dùng hoặc email đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        if (createUserDto.getRole() == Role.ADMIN) {
            throw new HttpException("Không thể tạo người dùng với quyền ADMIN", HttpStatus.FORBIDDEN);
        }

        var user = User.builder()
                .id(createUserDto.getId())
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .sex(createUserDto.getSex())
                .dateOfBirth(createUserDto.getDateOfBirth())
                .address(createUserDto.getAddress())
                .phone(createUserDto.getPhone())
                .email(createUserDto.getEmail())
                .role(createUserDto.getRole())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .build();

        return userRepository.save(user);
    }

    public User update(String userId, UpdateUserDto updateUserDto) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new HttpException("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST)
        );

        Optional.ofNullable(updateUserDto.getPassword()).ifPresent(password -> {
            user.setPassword(passwordEncoder.encode(password));
        });
        Optional.ofNullable(updateUserDto.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(updateUserDto.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(updateUserDto.getSex()).ifPresent(user::setSex);
        Optional.ofNullable(updateUserDto.getDateOfBirth()).ifPresent(user::setDateOfBirth);
        Optional.ofNullable(updateUserDto.getAddress()).ifPresent(user::setAddress);
        Optional.ofNullable(updateUserDto.getPhone()).ifPresent(user::setPhone);
        Optional.ofNullable(updateUserDto.getEmail()).ifPresent(email -> {
            if (!email.equals(user.getEmail()) && userRepository.existsByEmail(email)) {
                throw new HttpException("Email đã tồn tại", HttpStatus.BAD_REQUEST);
            }
            user.setEmail(email);
        });

        return userRepository.save(user);
    }

    public void delete(String userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new HttpException("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST)
        );

        if (user.getRole() == Role.ADMIN) {
            throw new HttpException("Không thể xóa người dùng với quyền ADMIN", HttpStatus.FORBIDDEN);
        }

        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAllByRoleIsNot(Role.ADMIN);
    }

    public User findById(String userId) {
        return userRepository.findByIdAndRoleIsNot(userId, Role.ADMIN).orElseThrow(
                () -> new HttpException("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST)
        );
    }

    public List<User> lookUpByIdOrName(String keyword) {
        return userRepository.lookUpByIdOrName(keyword);
    }
}
