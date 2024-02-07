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
@IdClass(RegionPriceId.class)
@Table(name = "THAYDOIGIADAYPHONG")
public class RegionPrice {
    @Id
    @Column(name = "NGAYQUYETDINH")
    private Date date;

    @Id
    @ManyToOne
    @JoinColumn(name = "MADAY")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "MAQL")
    private Staff staff;

    @Column(nullable = false, precision = 10, scale = 2, name = "GIAMOI")
    private BigDecimal price;
}

class RegionPriceId {
    private String date;
    private String region;
}