package com.ht.qlktx.entities;

import com.ht.qlktx.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAIPHONG")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALOAIPHONG")
    private Long id;

    @Column(name = "TENLOAIPHONG", columnDefinition = "NVARCHAR(100) NOT NULL UNIQUE")
    private String name;

    @Column(nullable = false, name = "SONGUOI")
    private int capacity;

    @Column(nullable = false, name = "GIOITINH", length = 10)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false, precision = 10, scale = 2, name = "DONGIA")
    private BigDecimal price;
}
