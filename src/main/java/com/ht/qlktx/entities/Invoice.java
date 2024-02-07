package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HOADON")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHD")
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2, name = "TONGTIENPHONG")
    private BigDecimal roomPrice;

    @Column(nullable = false, precision = 10, scale = 2, name = "TONGTIENDICHVU")
    private BigDecimal servicePrice;

    @Column(nullable = false, precision = 10, scale = 2, name = "TONGTIENPHUTHU")
    private BigDecimal extraChargePrice;

    @ManyToOne
    @JoinColumn(name = "MAQL")
    private User staff;

    @ManyToOne
    @JoinColumn(name = "MAPHIEUTHUE")
    private Booking booking;

    @CreationTimestamp
    @Column(name = "NGAYLAP")
    @JsonProperty("created_at")
    private Date createdAt;
}
