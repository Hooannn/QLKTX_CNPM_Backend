package com.ht.qlktx.modules.room_type;

import com.ht.qlktx.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    List<RoomType> findByNameContainingIgnoreCase(String keyword);
}
