package com.ht.qlktx.modules.booking.repositories;

import com.ht.qlktx.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByBookingTimeId(Long id);
}
