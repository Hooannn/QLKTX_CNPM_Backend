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
@Table(name = "DICHVU")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADV")
    private Long id;

    @Column(nullable = false, name = "TENDICHVU")
    private String name;

    @Column(nullable = false, precision = 10, scale = 2, name = "DONGIA")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "MANCC", nullable = false)
    private ServiceProvider provider;
}
