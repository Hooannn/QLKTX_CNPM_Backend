package com.ht.qlktx.entities;
/*
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
@Table(name = "THANHTOAN")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATHANHTOAN")
    private Long id;

    @Column(nullable = false, name = "NGAYTHANHTOAN")
    @JsonProperty("payment_date")
    private Date paymentDate;

    @OneToOne
    @JoinColumn(name = "MAHOADON", nullable = false, unique = true)
    private Invoice invoice;
}
*/