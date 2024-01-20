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
@Table(name = "region_prices")
public class RegionPrice {
    @Id
    private Date date;

    @JsonProperty
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @ManyToOne
    private Staff staff;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
