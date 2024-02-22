package com.ht.qlktx.modules.room_type;

import com.ht.qlktx.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    List<RoomType> findByNameContainingIgnoreCase(String keyword);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    List<RoomType> findByNameContainingIgnoreCaseAndDeletedIsFalse(String keyword);

    Optional<RoomType> findByIdAndDeletedIsFalse(Long id);

    List<RoomType> findAllByDeletedIsFalse();
}
