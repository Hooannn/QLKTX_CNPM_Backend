package com.ht.qlktx.modules.booking.repositories;

import com.ht.qlktx.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByBookingTimeId(Long id);

    boolean existsByRoomRegionIdAndDeletedIsFalse(String id);

    List<Booking> findAllByDeletedIsFalse();

    List<Booking> findAllByDeletedIsFalseAndCheckedOutAtIsNotNull();

    List<Booking> findAllByRoomIdAndDeletedIsFalse(String id);

    Long countByRoomIdAndDeletedIsFalse(String id);

    Optional<Booking> findByIdAndDeletedIsFalse(Long id);
}
