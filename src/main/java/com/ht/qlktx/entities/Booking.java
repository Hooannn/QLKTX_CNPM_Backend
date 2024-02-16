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
    @Column(name = "NGAYLAP")
    @JsonProperty("created_at")
    private Date createdAt;

    @Column(nullable = false, name = "NGAYBATDAU")
    @JsonProperty("start_date")
    private Date startDate;

    @Column(nullable = false, name = "NGAYKETTHUC")
    @JsonProperty("end_date")
    private Date endDate;

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
}
