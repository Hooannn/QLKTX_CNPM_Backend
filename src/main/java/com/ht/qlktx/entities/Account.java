package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "MaVaiTro", nullable = false)
    private Role role;

    @Column(nullable = false, unique = true, name = "Email")
    private String email;
}
