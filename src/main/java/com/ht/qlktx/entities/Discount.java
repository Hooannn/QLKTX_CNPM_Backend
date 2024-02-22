package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GIAMGIA")
@Check(constraints = "NGAYKETTHUC > NGAYBATDAU")
public class Discount {
    @Id
    @Column(name = "MAGG", length = 20)
    private String id;

    @Column(nullable = false, name = "NOIDUNG", columnDefinition = "NVARCHAR(255)", unique = true)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2, name = "PHANTRAM")
    private BigDecimal percentage;

    @ManyToOne
    @JoinColumn(name = "MAQL", nullable = false)
    private User staff;

    @Column(nullable = false, name = "NGAYBATDAU")
    @JsonProperty("start_date")
    private Date startDate;

    @Column(nullable = false, name = "NGAYKETTHUC")
    @JsonProperty("start_date")
    private Date endDate;

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;
}
