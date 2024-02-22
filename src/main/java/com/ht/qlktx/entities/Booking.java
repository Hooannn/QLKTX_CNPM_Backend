package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHIEUTHUE")
@Check(constraints = "NGAYLAP <= GETDATE() AND NGAYTRA >= NGAYLAP")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPHIEUTHUE")
    private Long id;

    @CreationTimestamp
    @Column(name = "NGAYLAP", nullable = false)
    @JsonProperty("created_at")
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "MATHOIGIANTHUE", nullable = false)
    @JsonProperty("booking_time")
    private BookingTime bookingTime;

    @ManyToOne
    @JoinColumn(name = "MAQL_NHAN", nullable = false)
    private User checkinStaff;

    @ManyToOne
    @JoinColumn(name = "MAPHONG", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "MASV", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "MAQL_TRA", nullable = true)
    private User checkoutStaff;

    @Column(name = "NGAYTRA", nullable = true)
    @JsonProperty("checked_out_at")
    private Date checkedOutAt;

    @OneToOne
    @JoinColumn(name = "MAGG", nullable = true)
    private Discount discount;

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;
}
