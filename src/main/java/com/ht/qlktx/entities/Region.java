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
@Table(name = "regions")
public class Region {
    @Id
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex target;

    @Column(nullable = false)
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "region", targetEntity = Room.class)
    private List<Room> rooms;

    @OneToMany(mappedBy = "region", targetEntity = RegionPrice.class)
    private List<RegionPrice> prices;
}
