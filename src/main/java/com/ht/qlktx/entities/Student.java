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
@Table(name = "SINHVIEN")
public class Student {
    @Id
    @Column(name = "MASV")
    private String id;

    @JsonProperty("first_name")
    @Column(nullable = false, name = "TEN")
    private String firstName;

    @JsonProperty("last_name")
    @Column(nullable = false, name = "HO")
    private String lastName;

    @Column(nullable = false, name = "PHAI")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JsonProperty("date_of_birth")
    @Column(name = "NGAYSINH")
    private Date dateOfBirth;

    @Column(name = "DIACHI")
    private String address;

    @JsonProperty("phone")
    @Column(name = "SDT")
    private String phone;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;
    /*
    @OneToOne
    @JoinColumn(name = "MATAIKHOAN", nullable = false, unique = true)
    private Account account;
    */
}
