package com.ht.qlktx.modules.room;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Room;
import com.ht.qlktx.enums.RoomStatus;
import com.ht.qlktx.modules.region.RegionService;
import com.ht.qlktx.modules.room.dtos.CreateRoomDto;
import com.ht.qlktx.modules.room.dtos.UpdateRoomDto;
import com.ht.qlktx.modules.room_type.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeService roomTypeService;
    private final RegionService regionService;

    public Room create(CreateRoomDto createRoomDto) {
        if (roomRepository.existsById(createRoomDto.getId())) {
            throw new HttpException("Mã phòng đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        var room = Room.builder()
                .id(createRoomDto.getId())
                .type(roomTypeService.findById(createRoomDto.getTypeId()))
                .region(regionService.findById(createRoomDto.getRegionId()))
                .deleted(false)
                .status(RoomStatus.AVAILABLE)
                .build();

        return roomRepository.save(room);
    }

    public void delete(String roomId) {
        // TODO: Check if there are any bookings associated with this room still active
    }

    public Room update(String roomId, UpdateRoomDto updateRoomDto) {
        var room = findById(roomId);

        Optional.ofNullable(updateRoomDto.getId()).ifPresent(room::setId);

        Optional.ofNullable(updateRoomDto.getRegionId()).ifPresent(regionId -> {
            room.setRegion(regionService.findById(regionId));
        });

        Optional.ofNullable(updateRoomDto.getTypeId()).ifPresent(typeId -> {
            room.setType(roomTypeService.findById(typeId));
        });

        Optional.ofNullable(updateRoomDto.getStatus()).ifPresent(room::setStatus);

        return roomRepository.save(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAllByDeletedFalse();
    }

    public Room findById(String roomId) {
        return roomRepository.findByIdAndDeletedFalse(roomId).orElseThrow(() -> new HttpException("Không tìm thấy phòng", HttpStatus.BAD_REQUEST));
    }

    public List<Room> lookUpById(String keyword) {
        return roomRepository.findByIdContainingIgnoreCaseAndDeletedFalse(keyword);
    }
}
