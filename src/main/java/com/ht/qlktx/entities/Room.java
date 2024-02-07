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

    @ManyToOne
    @JoinColumn(name = "MALOAIPHONG", nullable = false)
    private RoomType type;

    @Column(nullable = false, name = "TRANGTHAI")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
