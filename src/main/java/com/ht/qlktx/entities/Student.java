package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.enums.Sex;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SinhVien")
@Check(constraints = "NGAYSINH <= GETDATE()")
public class Student {
    @Id
    @Column(name = "MASV", length = 50)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "MASV", referencedColumnName = "MaTaiKhoan")
    private Account account;

    @JsonProperty("first_name")
    @Column(nullable = false, name = "TEN", columnDefinition = "NVARCHAR(50)")
    private String firstName;

    @JsonProperty("last_name")
    @Column(nullable = false, name = "HO", columnDefinition = "NVARCHAR(50)")
    private String lastName;

    @Column(nullable = false, name = "PHAI", length = 10)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JsonProperty("date_of_birth")
    @Column(name = "NGAYSINH", nullable = false)
    private Date dateOfBirth;

    @Column(name = "DIACHI", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String address;

    @JsonProperty("phone")
    @Column(name = "SDT", length = 15, unique = true)
    private String phone;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;
}
