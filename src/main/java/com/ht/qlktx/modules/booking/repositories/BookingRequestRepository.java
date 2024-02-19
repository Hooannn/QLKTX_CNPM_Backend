package com.ht.qlktx.modules.booking.repositories;

import com.ht.qlktx.entities.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRequestRepository extends JpaRepository<BookingRequest, Long> {
    List<BookingRequest> findAllByStudentIdOrderByCreatedAtAsc(String studentId);

    Optional<BookingRequest> findByIdAndStudentId(Long bookingRequestId, String studentId);
}
