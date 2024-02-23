package com.ht.qlktx.modules.booking.controllers;


import com.ht.qlktx.annotations.RequiredRole;
import com.ht.qlktx.config.Response;
import com.ht.qlktx.entities.Booking;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.projections.BookingView;
import com.ht.qlktx.modules.booking.dtos.CreateBookingDto;
import com.ht.qlktx.modules.booking.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<List<BookingView>>> findAll() {
        var bookings = bookingService.findAll();
        return ResponseEntity.ok(
                Response.<List<BookingView>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách phiếu thuê thành công")
                        .data(bookings)
                        .build()
        );
    }

    @GetMapping("/room/{roomId}/checked-out")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<List<BookingView>>> findAllCheckedOutByRoomId(@PathVariable String roomId) {
        var bookings = bookingService.findAllCheckedOutByRoomId(roomId);
        return ResponseEntity.ok(
                Response.<List<BookingView>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách phiếu thuê đã trả phòng thành công")
                        .data(bookings)
                        .build()
        );
    }

    @GetMapping("checked-out")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<List<BookingView>>> findAllCheckedOut() {
        var bookings = bookingService.findAllCheckedOut();
        return ResponseEntity.ok(
                Response.<List<BookingView>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách phiếu thuê đã trả phòng thành công")
                        .data(bookings)
                        .build()
        );
    }

    @PostMapping
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<Booking>> create(@Valid @RequestBody CreateBookingDto createBookingDto,
                                                    @RequestAttribute(name = "sub") String sub) {
        var booking = bookingService.create(createBookingDto, sub);
        return ResponseEntity.created(null).body(
                Response.<Booking>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Tạo phiếu thuê thành công")
                        .data(booking)
                        .build()
        );
    }
}
