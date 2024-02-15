package com.ht.qlktx.modules.room;

import com.ht.qlktx.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByIdContainingIgnoreCaseAndDeletedFalse(String keyword);

    List<Room> findAllByDeletedFalse();

    Optional<Room> findByIdAndDeletedFalse(String roomId);

    boolean existsByTypeIdAndDeletedFalse(Long roomTypeId);
}
