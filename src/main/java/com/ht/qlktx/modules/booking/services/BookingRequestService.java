package com.ht.qlktx.modules.booking.services;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.BookingRequest;
import com.ht.qlktx.enums.RequestStatus;
import com.ht.qlktx.modules.booking.dtos.CreateBookingRequestDto;
import com.ht.qlktx.modules.booking.repositories.BookingRequestRepository;
import com.ht.qlktx.modules.room.RoomService;
import com.ht.qlktx.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingRequestService {
    private final BookingRequestRepository bookingRequestRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final BookingTimeService bookingTimeService;

    public BookingRequest create(CreateBookingRequestDto createBookingRequestDto, String studentId) {
        var bookingRequest = BookingRequest.builder()
                .student(userService.findById(studentId))
                .room(roomService.findById(createBookingRequestDto.getRoomId()))
                .bookingTime(bookingTimeService.findByAvailableId(createBookingRequestDto.getBookingTimeId()))
                .status(RequestStatus.WAITING)
                .build();

        return bookingRequestRepository.save(bookingRequest);
    }

    public BookingRequest cancel(Long bookingRequestId, String studentId) {
        var bookingRequest = bookingRequestRepository.findByIdAndStudentId(bookingRequestId, studentId).orElseThrow(
                () -> new HttpException("Không tìm thấy yêu cầu đặt phòng", HttpStatus.BAD_REQUEST)
        );
        bookingRequest.setStatus(RequestStatus.CANCELED);
        return bookingRequestRepository.save(bookingRequest);
    }

    public List<BookingRequest> findAll() {
        return bookingRequestRepository.findAll(
                Sort.by(
                        Sort.Order.asc("createdAt")
                )
        );
    }

    public List<BookingRequest> findAllByStudentId(String studentId) {
        return bookingRequestRepository.findAllByStudentIdOrderByCreatedAtAsc(studentId);
    }
}
