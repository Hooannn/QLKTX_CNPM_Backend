package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "THOIGIANTHUE")
@Check(constraints = "NGAYBATDAU < NGAYKETTHUC")
public class BookingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATHOIGIANTHUE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MAQL", nullable = false)
    private User staff;

    @Column(name = "NOIDUNG", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String description;

    @Column(name = "NGAYBATDAU", nullable = false)
    @JsonProperty("start_date")
    private Date startDate;

    @Column(name = "NGAYKETTHUC", nullable = false)
    @JsonProperty("end_date")
    private Date endDate;

    @Column(nullable = false, name = "TRANGTHAIMO")
    private boolean open;

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;
}
