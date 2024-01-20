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
@Entity
@Table(name = "discount_prices")
public class DiscountPrice {
    @Id
    private Date date;

    @JsonProperty
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

    @ManyToOne
    private Staff staff;

    @Column(nullable = false)
    private double percent;
}