package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "YEUCAUTHUEPHONG")
public class BookingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAYEUCAU")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MASV", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "MAPHONG", nullable = false)
    private Room room;

    @Column(nullable = false, name = "NGAYBATDAU")
    @JsonProperty("start_date")
    private String startDate;

    @Column(nullable = false, name = "NGAYKETTHUC")
    @JsonProperty("end_date")
    private String endDate;

    @CreationTimestamp
    @Column(nullable = false, name = "NGAYYEUCAU")
    @JsonProperty("created_at")
    private String createdAt;

    @Column(nullable = false, name = "TRANGTHAI")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(nullable = true, name = "LYDOTUCHOI")
    @JsonProperty("reject_reason")
    private String rejectReason;

    @Column(nullable = true, name = "NGAYXULY")
    @JsonProperty("processed_at")
    private String processedAt;

    @ManyToOne
    @JoinColumn(nullable = true, name = "MAQL")
    private User staff;

    @OneToOne
    @JoinColumn(nullable = true, name = "MAPHIEUTHUE")
    private Booking booking;
}