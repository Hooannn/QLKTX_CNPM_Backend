package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extra_charge_prices")
public class ExtraChargePrice {
    @Id
    private Date date;

    @JsonProperty
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "extra_charge_id", referencedColumnName = "id")
    private ExtraCharge extraCharge;

    @ManyToOne
    private Staff staff;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
