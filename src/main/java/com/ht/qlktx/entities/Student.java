package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id;

    @JsonProperty("first_name")
    @Column(nullable = false)
    private String firstName;

    @JsonProperty("last_name")
    @Column(nullable = false)
    private String lastName;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("class_name")
    private String className;

    private String department;

    @JsonProperty("citizen_id")
    @Column(unique = true, nullable = false)
    private String citizenId;
}
