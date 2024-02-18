package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHIEUTHUE")
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
    @JoinColumn(name = "MAQL", nullable = false)
    private User staff;

    @ManyToOne
    @JoinColumn(name = "MAPHONG", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "MASV", nullable = false)
    private User student;

    @Column(name = "NGAYTRA", nullable = true)
    @JsonProperty("checked_out_at")
    private Date checkedOutAt;

    @OneToOne
    @JoinColumn(name = "MAGG", nullable = true)
    private Discount discount;
}
