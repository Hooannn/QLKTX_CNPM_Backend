package com.ht.qlktx;

import com.ht.qlktx.entities.*;
import com.ht.qlktx.enums.RoomStatus;
import com.ht.qlktx.modules.account.AccountRepository;
import com.ht.qlktx.modules.region.RegionRepository;
import com.ht.qlktx.modules.room.RoomRepository;
import com.ht.qlktx.modules.room_type.RoomTypeRepository;
import com.ht.qlktx.modules.staff.StaffRepository;
import com.ht.qlktx.modules.student.StudentRepository;
import com.ht.qlktx.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        List<Student> students = Helper.createSeedStudents();
        List<Staff> staffs = Helper.createSeedStaffs();
        List<Account> accounts = Helper.createSeedAccounts();
        List<RoomType> roomTypes = Helper.createSeedRoomTypes();
        List<Region> regions = Helper.createSeedRegions();

        List<Region> savedRegions;
        List<RoomType> savedRoomTypes;

        if (regionRepository.count() == 0 && regions != null) {
            savedRegions = regionRepository.saveAll(regions);
        } else {
            savedRegions = null;
        }

        if (roomTypeRepository.count() == 0 && roomTypes != null) {
            savedRoomTypes = roomTypeRepository.saveAll(roomTypes);
        } else {
            savedRoomTypes = null;
        }

        if (savedRegions != null && savedRoomTypes != null) {
            List<Room> generatedRooms = new ArrayList<>();
            savedRegions.forEach(region -> {
                for (int i = 0; i < roomTypes.size(); i++) {
                    String roomId = region.getId() + (i + 1);
                    Room room = Room.builder()
                            .id(roomId)
                            .region(region)
                            .type(roomTypes.get(i))
                            .deleted(false)
                            .status(RoomStatus.AVAILABLE)
                            .build();
                    generatedRooms.add(room);
                }
            });

            roomRepository.saveAll(generatedRooms);
        }

        if (accountRepository.count() == 0 && accounts != null) {
            List<Account> transformedAccounts = accounts.stream().peek(account -> account.setPassword(passwordEncoder.encode(account.getUsername()))).toList();
            accountRepository.saveAll(transformedAccounts);

            if (staffs != null) {
                staffs.forEach(staff -> {
                    System.out.println("[HOAN]: " + staff.getId());
                    staff.setAccount(accountRepository.findById(staff.getId()).orElse(null));
                    System.out.println("[HOAN] ----: " + staff.getAccount().getUsername());
                });
                staffRepository.saveAll(staffs);
            }

            if (students != null) {
                students.forEach(student -> {
                    System.out.println("[HOAN]: " + student.getId());
                    student.setAccount(accountRepository.findById(student.getId()).orElse(null));
                    System.out.println(student);
                });
                studentRepository.saveAll(students);
            }
        }
    }
}