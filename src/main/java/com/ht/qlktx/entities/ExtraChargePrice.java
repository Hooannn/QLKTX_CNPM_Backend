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
@IdClass(ExtraChargePriceId.class)
@Table(name = "THAYDOIGIAPHUTHU")
public class ExtraChargePrice {
    @Id
    @Column(name = "NGAYQUYETDINH")
    private Date date;

    @ManyToOne
    @Id
    @JoinColumn(name = "MAPT")
    private ExtraCharge extraCharge;

    @ManyToOne
    @JoinColumn(name = "MAQL")
    private Staff staff;

    @Column(nullable = false, precision = 10, scale = 2, name = "GIAMOI")
    private BigDecimal price;
}

class ExtraChargePriceId {
    private String date;
    private String extraCharge;
}