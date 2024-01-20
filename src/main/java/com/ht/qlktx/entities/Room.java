package com.ht.qlktx.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Region region;

    @ManyToOne
    private RoomType roomType;

    @OneToMany(mappedBy = "room", targetEntity = RoomStatus.class)
    private List<RoomStatus> roomStatuses;
}
