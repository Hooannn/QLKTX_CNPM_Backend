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
@Table(name = "PHUTHU")
public class ExtraCharge {
    @Id
    @Column(name = "MAPT")
    private String id;

    @Column(nullable = false, name = "LYDO")
    private String reason;
}
