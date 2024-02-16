package com.ht.qlktx.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GIAMGIA")
public class Discount {
    @Id
    @Column(name = "MAGG", length = 20)
    private String id;

    @Column(nullable = false, name = "NOIDUNG", columnDefinition = "NVARCHAR(255)")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2, name = "PHANTRAM")
    private BigDecimal percentage;

    @ManyToOne
    @JoinColumn(name = "MAQL", nullable = false)
    private User staff;
}
