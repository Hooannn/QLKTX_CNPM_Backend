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
@Table(name = "PHIEUTRA")
public class CheckoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUTRA")
    private Long id;

    @Column(nullable = false, name = "NGAYTRA")
    @JsonProperty("checkout_date")
    private Date checkoutDate;

    @OneToOne
    @JoinColumn(name = "MAPHIEUTHUE", nullable = false, unique = true)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "MAQL")
    private Staff staff;
}
