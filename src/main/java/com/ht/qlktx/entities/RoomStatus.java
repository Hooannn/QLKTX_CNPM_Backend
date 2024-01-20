package com.ht.qlktx.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_statuses")
public class RoomStatus {
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Id
    @Column
    private Date date;
}
