package com.ht.qlktx.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GIAMGIA")
public class Discount {
    @Id
    @Column(name = "MAGG")
    private String id;

    @Column(nullable = false, name = "NOIDUNG")
    private String description;
}
