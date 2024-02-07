package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DiscountPriceId.class)
@Entity
@Table(name = "THAYDOIPHANTRAMGIAMGIA")
public class DiscountPrice {
    @Id
    @Column(name = "NGAYQUYETDINH")
    private Date date;

    @Id
    @ManyToOne
    @JoinColumn(name = "MAGG")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "MAQL")
    private Staff staff;

    @Column(nullable = false, name = "PHANTRAMGIAM")
    private double percent;
}

class DiscountPriceId {
    private String date;
    private String discount;
}