package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ht.qlktx.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TaiKhoan")
public class Account {
    @Id
    @Column(name = "MaTaiKhoan")
    private String username;

    @Column(name = "MatKhau", nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false, name = "Role", length = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, unique = true, name = "Email")
    private String email;
}
