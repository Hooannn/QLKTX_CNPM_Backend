package com.ht.qlktx.modules.booking.controllers;

import com.ht.qlktx.annotations.RequiredRole;
import com.ht.qlktx.config.Response;
import com.ht.qlktx.entities.BookingRequest;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.booking.dtos.CreateBookingRequestDto;
import com.ht.qlktx.modules.booking.services.BookingRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/booking/request")
public class BookingRequestController {
    private final BookingRequestService bookingRequestService;

    @PostMapping
    @RequiredRole({Role.STUDENT})
    public ResponseEntity<Response<BookingRequest>> create(@Valid @RequestBody CreateBookingRequestDto createBookingRequestDto,
                                                           @RequestAttribute(name = "sub") String sub) {
        var bookingRequest = bookingRequestService.create(createBookingRequestDto, sub);
        return ResponseEntity.created(null).body(
                Response.<BookingRequest>builder()
                        .data(bookingRequest)
                        .status(HttpStatus.CREATED.value())
                        .message("Yêu cầu đặt phòng thành công")
                        .build()
        );
    }

    @GetMapping
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<List<BookingRequest>>> findAll() {
        var bookingRequests = bookingRequestService.findAll();
        return ResponseEntity.ok(
                Response.<List<BookingRequest>>builder()
                        .data(bookingRequests)
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách yêu cầu đặt phòng thành công")
                        .build()
        );
    }

    @GetMapping("/own")
    @RequiredRole({Role.STUDENT})
    public ResponseEntity<Response<List<BookingRequest>>> findAllByStudentId(@RequestAttribute(name = "sub") String sub) {
        var bookingRequests = bookingRequestService.findAllByStudentId(sub);
        return ResponseEntity.ok(
                Response.<List<BookingRequest>>builder()
                        .data(bookingRequests)
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách yêu cầu đặt phòng thành công")
                        .build()
        );
    }

    @GetMapping("/user/{userId}")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<List<BookingRequest>>> findAllByUserId(@PathVariable String userId) {
        var bookingRequests = bookingRequestService.findAllByStudentId(userId);
        return ResponseEntity.ok(
                Response.<List<BookingRequest>>builder()
                        .data(bookingRequests)
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách yêu cầu đặt phòng thành công")
                        .build()
        );
    }

    @PostMapping("/{requestId}/cancel")
    @RequiredRole({Role.STUDENT})
    public ResponseEntity<Response<BookingRequest>> cancel(@PathVariable Long requestId,
                                                           @RequestAttribute(name = "sub") String sub) {
        var bookingRequest = bookingRequestService.cancel(requestId, sub);
        return ResponseEntity.ok(
                Response.<BookingRequest>builder()
                        .data(bookingRequest)
                        .status(HttpStatus.OK.value())
                        .message("Hủy yêu cầu đặt phòng thành công")
                        .build()
        );
    }
}
