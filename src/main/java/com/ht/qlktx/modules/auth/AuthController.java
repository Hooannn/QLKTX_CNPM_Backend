package com.ht.qlktx.modules.auth;

import com.ht.qlktx.config.Response;
import com.ht.qlktx.modules.auth.dtos.AuthenticateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/authenticate")
    public ResponseEntity<Response<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticateDto authenticateDto) {
        var response = authService.authenticate(authenticateDto);
        return ResponseEntity.ok(
                Response.<AuthenticationResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Đăng nhập thành công")
                        .data(response)
                        .build()
        );
    }
}
