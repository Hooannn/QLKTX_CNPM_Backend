package com.ht.qlktx;

import com.ht.qlktx.entities.Region;
import com.ht.qlktx.entities.Room;
import com.ht.qlktx.entities.RoomType;
import com.ht.qlktx.entities.User;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.enums.RoomStatus;
import com.ht.qlktx.enums.Sex;
import com.ht.qlktx.modules.region.RegionRepository;
import com.ht.qlktx.modules.room.RoomRepository;
import com.ht.qlktx.modules.room_type.RoomTypeRepository;
import com.ht.qlktx.modules.user.UserRepository;
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
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        List<User> students = Helper.createSeedStudents();
        List<User> staffs = Helper.createSeedStaffs();
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

        if (userRepository.count() == 0 && students != null && staffs != null) {
            staffs.forEach(staff -> staff.setPassword(passwordEncoder.encode(staff.getId())));
            students.forEach(student -> student.setPassword(passwordEncoder.encode(student.getId())));
            userRepository.saveAll(students);
            userRepository.saveAll(staffs);
        }

        if (userRepository.count() == 0 && students != null && staffs != null) {
            staffs.forEach(staff -> staff.setPassword(passwordEncoder.encode(staff.getId())));
            students.forEach(student -> student.setPassword(passwordEncoder.encode(student.getId())));
            userRepository.saveAll(students);
            userRepository.saveAll(staffs);
        }
        //Create default admin account
        String defaultAdminUsername = "admin001";
        String defaultAdminPassword = "123456";
        if (userRepository.existsById(defaultAdminUsername)) {
            return;
        }
        User user = User.builder()
                .id(defaultAdminUsername)
                .password(passwordEncoder.encode(defaultAdminPassword))
                .sex(Sex.MALE)
                .firstName("Admin")
                .lastName("Admin")
                .email("admin@qlktx.ptithcm.edu.vn")
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
}