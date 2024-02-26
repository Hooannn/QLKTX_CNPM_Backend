package com.ht.qlktx.modules.statistic;

import com.ht.qlktx.enums.Role;
import com.ht.qlktx.enums.RoomStatus;
import com.ht.qlktx.modules.invoice.InvoiceRepository;
import com.ht.qlktx.modules.region.RegionRepository;
import com.ht.qlktx.modules.room.RoomRepository;
import com.ht.qlktx.modules.statistic.dtos.StatisticOverview;
import com.ht.qlktx.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final RoomRepository roomRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final InvoiceRepository invoiceRepository;
    public StatisticOverview getOverview() {
        var totalRooms = roomRepository.countByDeletedIsFalse();
        var totalRegions = regionRepository.countByDeletedIsFalse();
        var totalStudents = userRepository.countByDeletedIsFalseAndRoleIs(Role.STUDENT);
        var totalInvoices = invoiceRepository.countByDeletedIsFalse();
        var totalMaintainingRooms = roomRepository.countByDeletedIsFalseAndStatusIs(RoomStatus.MAINTAINING);
        var totalUnpaidInvoices = invoiceRepository.countByDeletedIsFalseAndPaidAtIsNull();
        //totalEmptyRooms
        //totalBookedStudents
        //roomTypeStatistics
        return null;
    }
}
