package com.ht.qlktx.modules.auth;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Account;
import com.ht.qlktx.entities.Staff;
import com.ht.qlktx.entities.Student;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.filter.JwtService;
import com.ht.qlktx.modules.account.AccountRepository;
import com.ht.qlktx.modules.auth.dtos.AuthenticateDto;
import com.ht.qlktx.modules.staff.StaffRepository;
import com.ht.qlktx.modules.student.StudentRepository;
import com.ht.qlktx.utils.MailService;
import com.ht.qlktx.utils.RedisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthService.class);
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final RedisService redisService;
    public AuthenticationResponse<?> authenticate(AuthenticateDto authenticateDto) {
        String errorMessage = "Tài khoản hoặc mật khẩu không hợp lệ";

        Account account = accountRepository.findByUsername(authenticateDto.getUsername())
                .orElseThrow(() -> new HttpException(errorMessage, HttpStatus.UNAUTHORIZED));

        if (passwordEncoder.matches(authenticateDto.getPassword(), account.getPassword())) {
            String accessToken = jwtService.generateAccessToken(account);

            Role role = account.getRole();

            if (role == Role.STUDENT) {
                Student user = studentRepository.findByIdAndDeletedIsFalse(account.getUsername())
                        .orElseThrow(() -> new HttpException(errorMessage, HttpStatus.UNAUTHORIZED));
                return new AuthenticationResponse<>(
                        new Credentials(accessToken),
                        user
                );
            } else {
                Staff user = staffRepository.findByIdAndDeletedIsFalse(account.getUsername())
                        .orElseThrow(() -> new HttpException(errorMessage, HttpStatus.UNAUTHORIZED));
                return new AuthenticationResponse<>(
                        new Credentials(accessToken),
                        user
                );
            }
        }

        throw new HttpException(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    public void forgotPassword(String email) {
        /*
        if (!userRepository.existsByEmailAndDeletedIsFalse(email))
            throw new HttpException("Tài khoản không tồn tại", HttpStatus.NOT_FOUND);

        var token = jwtService.generateResetPasswordToken(email);
        CompletableFuture.runAsync(() -> {
            try {
                mailService.sendForgotPasswordMail(email, token);
            } catch (Exception e) {
                logger.error("Có lỗi xảy ra khi gửi mail: {}", e.getMessage());
            }
        });
        */
    }

    public void resetPassword(String token, String newPassword) {
        /*
        var result = jwtService.verifyResetPasswordToken(token);

        if (!result.isTokenValid())
            throw new HttpException("Token không hợp lệ", HttpStatus.FORBIDDEN);

        String email = result.subject();

        User user = userRepository.findByEmailAndDeletedIsFalse(email)
                .orElseThrow(() -> new HttpException("Tài khoản không tồn tại", HttpStatus.NOT_FOUND));

        user.setPassword(passwordEncoder.encode(newPassword));
        redisService.deleteValue("reset_password_token:" + email);
        userRepository.save(user);
        */
    }
}
