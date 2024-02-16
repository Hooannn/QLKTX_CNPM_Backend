package com.ht.qlktx.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "MADAY", length = 20)
    private String id;

    @OneToMany(mappedBy = "region", targetEntity = Room.class)
    @JsonManagedReference
    private List<Room> rooms;
}
