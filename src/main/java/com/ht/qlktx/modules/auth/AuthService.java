package com.ht.qlktx.modules.auth;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.User;
import com.ht.qlktx.filter.JwtService;
import com.ht.qlktx.modules.auth.dtos.AuthenticateDto;
import com.ht.qlktx.modules.user.UserRepository;
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
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final RedisService redisService;
    public AuthenticationResponse authenticate(AuthenticateDto authenticateDto) {
        String errorMessage = "Tài khoản hoặc mật khẩu không hợp lệ";
        User user = userRepository.findById(authenticateDto.getUsername())
                .orElseThrow(() ->  new HttpException(errorMessage, HttpStatus.UNAUTHORIZED));

        if (passwordEncoder.matches(authenticateDto.getPassword(), user.getPassword())) {
            String accessToken = jwtService.generateAccessToken(user);
            return new AuthenticationResponse(
                    new Credentials(accessToken),
                    user
            );
        }

        throw new HttpException(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    public void forgotPassword(String email) {
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
    }

    public void resetPassword(String token, String newPassword) {
        var result = jwtService.verifyResetPasswordToken(token);

        if (!result.isTokenValid())
            throw new HttpException("Token không hợp lệ", HttpStatus.FORBIDDEN);

        String email = result.subject();

        User user = userRepository.findByEmailAndDeletedIsFalse(email)
                .orElseThrow(() -> new HttpException("Tài khoản không tồn tại", HttpStatus.NOT_FOUND));

        user.setPassword(passwordEncoder.encode(newPassword));
        redisService.deleteValue("reset_password_token:" + email);
        userRepository.save(user);
    }
}
