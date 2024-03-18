package com.ht.qlktx.modules.staff.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.enums.Sex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
public class CreateStaffDto {
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Length(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;

    @NotEmpty(message = "Mã người dùng không được để trống")
    private String id;

    @NotEmpty(message = "Tên không được để trống")
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty(message = "Họ không được để trống")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Giới tính không được để trống")
    private Sex sex;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String address;

    private String phone;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;
}
