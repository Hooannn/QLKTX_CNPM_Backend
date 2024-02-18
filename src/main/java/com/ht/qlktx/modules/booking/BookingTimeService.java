package com.ht.qlktx.modules.booking;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.BookingTime;
import com.ht.qlktx.modules.booking.dtos.CreateBookingTimeDto;
import com.ht.qlktx.modules.booking.dtos.UpdateBookingTimeDto;
import com.ht.qlktx.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingTimeService {
    private final UserService userService;
    private final BookingTimeRepository bookingTimeRepository;
    private final BookingRepository bookingRepository;

    public BookingTime create(CreateBookingTimeDto createBookingTimeDto, String staffId) {
        if (createBookingTimeDto.getEndDate().before(createBookingTimeDto.getStartDate()) || createBookingTimeDto.getEndDate().equals(createBookingTimeDto.getStartDate()))
            throw new HttpException("Thời gian bắt đầu phải bé hơn thời gian kết thúc", HttpStatus.BAD_REQUEST);

        BookingTime bookingTime = BookingTime.builder()
                .startDate(createBookingTimeDto.getStartDate())
                .endDate(createBookingTimeDto.getEndDate())
                .description(createBookingTimeDto.getDescription())
                .open(createBookingTimeDto.isOpen())
                .staff(userService.findById(staffId))
                .build();

        return bookingTimeRepository.save(bookingTime);
    }

    public void delete(Long id) {
        if (bookingRepository.existsByBookingTimeId(id)) {
            throw new HttpException("Không thể xóa vì đã có phiếu thuê đang sử dụng", HttpStatus.BAD_REQUEST);
        }

        //TODO: Check booking request, extension request
        bookingTimeRepository.deleteById(id);
    }

    public BookingTime update(Long id, UpdateBookingTimeDto updateBookingTimeDto) {
        var bookingTime = bookingTimeRepository.findById(id).orElseThrow(() -> new HttpException("Không tìm thấy thời gian thuê", HttpStatus.BAD_REQUEST));

        Optional.ofNullable(updateBookingTimeDto.getDescription()).ifPresent(bookingTime::setDescription);
        Optional.ofNullable(updateBookingTimeDto.getEndDate()).ifPresent(bookingTime::setEndDate);
        Optional.ofNullable(updateBookingTimeDto.getStartDate()).ifPresent(bookingTime::setStartDate);
        Optional.of(updateBookingTimeDto.isOpen()).ifPresent(bookingTime::setOpen);

        if (bookingTime.getEndDate().before(bookingTime.getStartDate()) || bookingTime.getEndDate().equals(bookingTime.getStartDate()))
            throw new HttpException("Thời gian bắt đầu phải bé hơn thời gian kết thúc", HttpStatus.BAD_REQUEST);

        return bookingTimeRepository.save(bookingTime);
    }

    public List<BookingTime> findAll() {
        return bookingTimeRepository.findAll();
    }

    public List<BookingTime> findAllAvailable() {
        return bookingTimeRepository.findAllByOpenIsTrueAndStartDateIsAfter(new Date());
    }
}
