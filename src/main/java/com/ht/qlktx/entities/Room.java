package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "MAPHONG", length = 20)
    private String id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "MADAY", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "MALOAIPHONG", nullable = false)
    private RoomType type;

    @Column(nullable = false, name = "XOA", columnDefinition = "BIT DEFAULT 0")
    private boolean deleted;

    @Column(nullable = false, name = "TRANGTHAI", length = 20)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
