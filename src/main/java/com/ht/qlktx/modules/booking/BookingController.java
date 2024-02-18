package com.ht.qlktx.modules.booking;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

}
