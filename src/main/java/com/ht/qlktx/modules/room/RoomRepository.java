package com.ht.qlktx.modules.room;

import com.ht.qlktx.entities.Room;
import com.ht.qlktx.projections.RoomWithBookingCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByIdContainingIgnoreCaseAndDeletedFalse(String keyword);

    List<Room> findAllByDeletedFalse();

    @Query("""
        SELECT r.id as id, r.region as region, r.type as type, r.status as status, COUNT(b.id) as bookingCount FROM Room r
        LEFT JOIN r.type
        LEFT JOIN r.bookings b on b.room.id = r.id and b.checkedOutAt is null
        WHERE r.deleted = false
        GROUP BY r, r.type, r.region
    """)
    List<RoomWithBookingCountView> findAllWithBookingCount();

    Optional<Room> findByIdAndDeletedFalse(String roomId);

    @Query("""
        SELECT r FROM Room r
        LEFT JOIN FETCH r.bookings b
        WHERE r.id = :roomId and r.deleted = false and b.checkedOutAt is null
    """)
    Optional<Room> findByIdWithBooking(String roomId);

    boolean existsByTypeIdAndDeletedFalse(Long roomTypeId);

    boolean existsByRegionIdAndDeletedIsFalse(String id);
}
