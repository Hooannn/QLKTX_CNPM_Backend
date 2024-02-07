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
@Table(name = "PHONG")
public class Room {
    @Id
    @Column(name = "MAPHONG")
    private String id;

    @ManyToOne
    @JoinColumn(name = "MADAY", nullable = false)
    private Region region;

    @Column(nullable = false, columnDefinition = "int default 1", name = "SONGUOIOTOIDA")
    private int capacity;

    @Column(nullable = false, name = "TRANGTHAI")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
