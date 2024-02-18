package com.ht.qlktx.modules.booking;

import com.ht.qlktx.entities.BookingTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingTimeRepository extends JpaRepository<BookingTime, Long> {
    List<BookingTime> findAllByOpenIsTrueAndStartDateIsAfter(Date date);
}
