package com.ht.qlktx.modules.student.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.enums.Sex;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateStudentDto {
    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String phone;

    private Sex sex;

    @Email(message = "Email is invalid")
    private String email;
}
