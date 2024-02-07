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
@IdClass(ElectricityConsumptionId.class)
@Table(name = "TIEUTHUDIEN")
public class ElectricityConsumption {
    @Id
    @ManyToOne
    @JoinColumn(name = "MAPHONG", nullable = false)
    private Room room;

    @Id
    @Column(name = "THANG")
    private int month;

    @Id
    @Column(name = "NAM")
    private int year;

    @Column(name = "CHISO")
    private int index;

    @CreationTimestamp
    @Column(name = "NGAYGHI")
    @JsonProperty("created_at")
    private Date createdAt;
}

class ElectricityConsumptionId implements java.io.Serializable {
    private String room;
    private String month;
    private String year;
}
