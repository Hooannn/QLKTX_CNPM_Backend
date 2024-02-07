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
@Table(name = "YEUCAUCHUYENPHONG")
public class ChangeRoomRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAYEUCAU")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MAPHONGCHUYEN", nullable = false)
    private Room room;

    @OneToOne
    @JoinColumn(nullable = false, name = "MAPHIEUTHUE")
    private Booking booking;

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
}
