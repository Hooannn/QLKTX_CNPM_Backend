package com.ht.qlktx.modules.rooms;

import com.ht.qlktx.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
