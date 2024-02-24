package com.ht.qlktx.modules.booking.services;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Booking;
import com.ht.qlktx.modules.discount.DiscountService;
import com.ht.qlktx.modules.invoice.InvoiceService;
import com.ht.qlktx.projections.BookingView;
import com.ht.qlktx.modules.booking.dtos.CreateBookingDto;
import com.ht.qlktx.modules.booking.repositories.BookingRepository;
import com.ht.qlktx.modules.room.RoomService;
import com.ht.qlktx.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final DiscountService discountService;
    private final BookingTimeService bookingTimeService;
    private final RoomService roomService;
    private final InvoiceService invoiceService;
    private final UserService userService;
    public List<BookingView> findAll() {
        return bookingRepository.findAllByDeletedIsFalse();
    }

    public List<BookingView> findAllCheckedOut() {
        return bookingRepository.findAllByDeletedIsFalseAndCheckedOutAtIsNotNull();
    }

    public Booking create(CreateBookingDto createBookingDto, String checkinStaffId) {
        var bookingTime = bookingTimeService.findByAvailableId(createBookingDto.getBookingTimeId());
        var checkinStaff = userService.findById(checkinStaffId);
        var room = roomService.findById(createBookingDto.getRoomId());
        var student = userService.findById(createBookingDto.getStudentId());
        var bookingsInRoom = bookingRepository.countByRoomIdAndDeletedIsFalseAndCheckedOutAtIsNull(room.getId());

        if (!student.isStudent())
            throw new HttpException("Người dùng không phải là sinh viên", HttpStatus.BAD_REQUEST);

        if (bookingsInRoom >= room.getType().getCapacity())
            throw new HttpException("Phòng đã đầy", HttpStatus.BAD_REQUEST);

        if (!room.getRegion().getSex().equals(student.getSex()))
            throw new HttpException("Phòng không phù hợp với giới tính của sinh viên", HttpStatus.BAD_REQUEST);

        var booking = Booking.builder()
                .bookingTime(bookingTime)
                .checkinStaff(checkinStaff)
                .room(room)
                .createdAt(new Date())
                .student(student)
                .build();

        if (createBookingDto.getDiscountId() != null) {
            var discount = discountService.findByAvailableId(createBookingDto.getDiscountId());
            booking.setDiscount(discount);
        }

        var savedBooking = bookingRepository.save(booking);

        if (createBookingDto.isAutoCreateInvoice()) {
            invoiceService.create(savedBooking, checkinStaff, savedBooking.getTotalPrice());
        }

        return savedBooking;
    }

    public Booking findById(Long id) {
        return bookingRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() ->
                new HttpException("Không tìm thấy phiếu thuê", HttpStatus.NOT_FOUND)
        );
    }

    public <T> T findById(Long id, Class<T> type) {
        return bookingRepository.findByIdAndDeletedIsFalse(id, type).orElseThrow(() ->
                new HttpException("Không tìm thấy phiếu thuê", HttpStatus.NOT_FOUND)
        );
    }

    public Booking checkOut(Long id, String checkOutStaffId) {
        return checkOut(id, checkOutStaffId, new Date());
    }

    public Booking checkOut(Long id, String checkOutStaffId, Date checkedOutAt) {
        var booking = findById(id);

        if (booking.isCheckedOut())
            throw new HttpException("Phiếu thuê đã được trả", HttpStatus.BAD_REQUEST);

        var checkOutStaff = userService.findById(checkOutStaffId);
        booking.setCheckedOutAt(checkedOutAt);
        booking.setCheckoutStaff(checkOutStaff);
        return bookingRepository.save(booking);
    }

    public List<BookingView> findAllCheckedOutByRoomId(String roomId) {
        return bookingRepository.findAllByRoomIdAndDeletedIsFalseAndCheckedOutAtIsNotNull(roomId);
    }

    public BigDecimal calculatePrice(Long bookingTimeId, String roomId, String discountId) {
        var bookingTime = bookingTimeService.findByAvailableId(bookingTimeId);
        var room = roomService.findById(roomId);
        var discount = discountId != null ? discountService.findByAvailableId(discountId) : null;
        var unitPrice = room.getType().getPrice();
        var duration = bookingTime.getDurationInMonths();
        if (discount != null) {
            var percentage = discount.getPercentage();
            return unitPrice.multiply(BigDecimal.valueOf(duration))
                    .multiply(BigDecimal.valueOf(1)
                            .subtract(percentage.divide(BigDecimal.valueOf(100))));
        }
        return unitPrice.multiply(BigDecimal.valueOf(duration));
    }

    public List<BookingView> findAllByStudentId(String studentId) {
        return bookingRepository.findAllByStudentIdAndDeletedIsFalse(studentId);
    }
}
