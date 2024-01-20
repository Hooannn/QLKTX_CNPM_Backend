package com.ht.qlktx.entities;

import com.ht.qlktx.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    private String id;

    @ManyToOne
    private Region region;

    @Column(nullable = false, columnDefinition = "int default 1 check (capacity >= 1)")
    private int capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
