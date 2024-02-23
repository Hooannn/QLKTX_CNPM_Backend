package com.ht.qlktx.modules.invoice;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Booking;
import com.ht.qlktx.entities.Invoice;
import com.ht.qlktx.entities.User;
import com.ht.qlktx.modules.booking.repositories.BookingRepository;
import com.ht.qlktx.modules.invoice.dtos.CreateInvoiceDto;
import com.ht.qlktx.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    public Invoice create(CreateInvoiceDto createInvoiceDto, String sub) {
        var booking = bookingRepository.findByIdAndDeletedIsFalse(createInvoiceDto.getBookingId()).orElseThrow(
                () -> new HttpException("Không tìm thấy phiếu thuê", HttpStatus.BAD_REQUEST)
        );
        var staff = userRepository.findByIdAndDeletedIsFalse(sub).orElseThrow(
                () -> new HttpException("Nhân viên không tồn tại", HttpStatus.BAD_REQUEST)
        );
        return create(booking, staff, createInvoiceDto.getTotal());
    }

    public Invoice create(Booking booking, User staff, BigDecimal total) {
        Invoice invoice = Invoice.builder()
                .booking(booking)
                .staff(staff)
                .total(total)
                .createdAt(new Date())
                .build();
        return invoiceRepository.save(invoice);
    }
}
