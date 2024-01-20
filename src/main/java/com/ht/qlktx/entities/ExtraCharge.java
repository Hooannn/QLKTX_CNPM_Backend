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
@Table(name = "extra_charges")
public class ExtraCharge {
    @Id
    private String id;

    @Column(nullable = false)
    private String reason;
}
