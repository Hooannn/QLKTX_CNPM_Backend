package com.ht.qlktx.modules.student.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("department")
    private String department;

    @JsonProperty("citizen_id")
    private String citizenId;
}
