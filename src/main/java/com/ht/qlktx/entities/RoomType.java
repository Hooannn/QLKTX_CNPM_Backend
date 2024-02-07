package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ht.qlktx.enums.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAIPHONG")
public class RoomType {
    @Id
    @Column(name = "MALOAIPHONG")
    private Long id;

    @Column(nullable = false, name = "TENLOAIPHONG")
    private String name;

    @Column(nullable = false, name = "SONGUOI")
    private int capacity;

    @Column(nullable = false, name = "GIOITINH")
    private Sex sex;

    @Column(nullable = false, precision = 10, scale = 2, name = "DONGIA")
    private BigDecimal price;

    @JsonIgnore
    @OneToMany(mappedBy = "type", targetEntity = Room.class)
    private List<Room> rooms;
}
