package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
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
@Check(constraints = "NGAYLAP <= GETDATE() AND TONGTIENPHONG >= 0 AND NGAYTHANHTOAN <= GETDATE()")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHDDV")
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2, name = "TONGTIENPHONG")
    private BigDecimal total;

    @Column(nullable = true, name = "NGAYTHANHTOAN")
    @JsonProperty("paid_at")
    private Date paidAt;

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

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;
}
