package com.ht.qlktx.modules.auth;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.User;
import com.ht.qlktx.filter.JwtService;
import com.ht.qlktx.modules.auth.dtos.AuthenticateDto;
import com.ht.qlktx.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
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
}
