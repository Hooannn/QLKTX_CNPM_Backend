package com.ht.qlktx.entities;

import com.ht.qlktx.enums.Sex;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAIPHONG")
@Check(constraints = "SONGUOI >= 0 AND DONGIA >= 0")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALOAIPHONG")
    private Long id;

    @Column(name = "TENLOAIPHONG", columnDefinition = "NVARCHAR(100) NOT NULL UNIQUE")
    private String name;

    @Column(nullable = false, name = "SONGUOI")
    private int capacity;

    @Column(nullable = false, precision = 10, scale = 2, name = "DONGIA")
    private BigDecimal price;

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;
}
