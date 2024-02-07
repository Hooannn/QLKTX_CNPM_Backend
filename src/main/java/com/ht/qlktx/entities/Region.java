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

    @JsonIgnore
    @OneToMany(mappedBy = "region", targetEntity = Room.class)
    private List<Room> rooms;
}
