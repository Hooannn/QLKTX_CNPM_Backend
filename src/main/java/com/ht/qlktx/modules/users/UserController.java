package com.ht.qlktx.modules.users;

import com.ht.qlktx.annotations.RequiredRole;
import com.ht.qlktx.config.Response;
import com.ht.qlktx.entities.User;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.users.dtos.CreateUserDto;
import com.ht.qlktx.modules.users.dtos.UpdateUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @RequiredRole({Role.ADMIN})
    public ResponseEntity<Response<Iterable<User>>> findAll() {
        var users = userService.findAll();
        return ResponseEntity.ok(
                Response.<Iterable<User>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách người dùng thành công")
                        .data(users)
                        .build()
        );
    }

    @GetMapping(path = "/{userId}")
    @RequiredRole({Role.ADMIN})
    public ResponseEntity<Response<User>> findById(@PathVariable String userId) {
        var user = userService.findById(userId);
        return ResponseEntity.ok(
                Response.<User>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tìm người dùng thành công")
                        .data(user)
                        .build()
        );
    }

    @PostMapping
    @RequiredRole({Role.ADMIN})
    public ResponseEntity<Response<User>> create(@Valid @RequestBody CreateUserDto createUserDto) {
        var user = userService.create(createUserDto);
        return ResponseEntity.created(null).body(
                Response.<User>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Tạo người dùng thành công")
                        .data(user)
                        .build()
        );
    }

    @PutMapping(path = "/{userId}")
    @RequiredRole({Role.ADMIN})
    public ResponseEntity<Response<User>> update(@PathVariable String userId, @Valid @RequestBody UpdateUserDto updateUserDto) {
        var user = userService.update(userId, updateUserDto);
        return ResponseEntity.ok(
                Response.<User>builder()
                        .status(HttpStatus.OK.value())
                        .message("Cập nhật người dùng thành công")
                        .data(user)
                        .build()
        );
    }

    @DeleteMapping(path = "/{userId}")
    @RequiredRole({Role.ADMIN})
    public ResponseEntity<Response<?>> delete(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.ok(
                Response.<User>builder()
                        .status(HttpStatus.OK.value())
                        .message("Xóa người dùng thành công")
                        .build()
        );
    }

    @GetMapping(path = "/lookup")
    @RequiredRole({Role.ADMIN})
    public ResponseEntity<Response<Iterable<User>>> lookUpByIdOrName(@RequestParam() String keyword) {
        var users = userService.lookUpByIdOrName(keyword);
        return ResponseEntity.ok(
                Response.<Iterable<User>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tìm kiếm người dùng thành công")
                        .data(users)
                        .build()
        );
    }
}
