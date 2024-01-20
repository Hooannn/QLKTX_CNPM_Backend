package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_types")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    private int maxPeople;

    private int price;

    @JsonIgnore
    @OneToMany(mappedBy = "roomType", targetEntity = Room.class)
    private List<Room> rooms;
}
