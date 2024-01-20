package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    private String id;

    @JsonProperty("first_name")
    @Column(nullable = false)
    private String firstName;

    @JsonProperty("last_name")
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String address;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;
}
