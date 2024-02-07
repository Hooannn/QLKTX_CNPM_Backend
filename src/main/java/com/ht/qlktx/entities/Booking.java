package com.ht.qlktx.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

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
    private Date createdAt;

    @Column(nullable = false, name = "NGAYBATDAU")
    private Date startDate;

    @Column(nullable = false, name = "NGAYKETTHUC")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "MAQL", nullable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "MAPHONG", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "MASV", nullable = false)
    private Student student;

    @ManyToMany
    @JoinTable(
            name = "CT_PHIEUTHUE_DV",
            joinColumns = @JoinColumn(name = "MAPHIEUTHUE"),
            inverseJoinColumns = @JoinColumn(name = "MADV")
    )
    private Set<Service> services;

    @ManyToMany
    @JoinTable(
            name = "CT_PHIEUTHUE_PT",
            joinColumns = @JoinColumn(name = "MAPHIEUTHUE"),
            inverseJoinColumns = @JoinColumn(name = "MAPT")
    )
    private Set<ExtraCharge> extraCharges;
}
