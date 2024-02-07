package com.ht.qlktx.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NHACUNGCAPDICHVU")
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANCC")
    private Long id;

    @Column(nullable = false, name = "TENNHACUNGCAP")
    private String name;

    @Column(nullable = false, name = "DIACHI")
    private String address;

    @Column(nullable = false, name = "SDT")
    private String phone;

    @Column(nullable = false, name = "EMAIL")
    private String email;
}
