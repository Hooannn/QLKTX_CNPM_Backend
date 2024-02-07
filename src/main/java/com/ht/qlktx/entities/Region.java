package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ht.qlktx.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DAYPHONG")
public class Region {
    @Id
    @Column(name = "MADAY")
    private String id;

    @Column(nullable = false, name = "DOITUONGSUDUNG")
    @Enumerated(EnumType.STRING)
    private Sex target;

    @Column(nullable = false, name = "LOAI")
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "region", targetEntity = Room.class)
    private List<Room> rooms;

    @OneToMany(mappedBy = "region", targetEntity = RegionPrice.class)
    private List<RegionPrice> prices;
}
