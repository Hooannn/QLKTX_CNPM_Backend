package com.ht.qlktx.modules.rooms;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Room;
import com.ht.qlktx.enums.RoomStatus;
import com.ht.qlktx.modules.region.RegionService;
import com.ht.qlktx.modules.rooms.dtos.CreateRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RegionService regionService;

    public Room create(CreateRoomDto createRoomDto) {
        var region = regionService.findById(createRoomDto.getRegionId());

        var room = Room.builder()
                .id(createRoomDto.getId())
                .region(region)
                .capacity(createRoomDto.getCapacity())
                .status(RoomStatus.AVAILABLE)
                .build();

        return roomRepository.save(room);
    }

    public Room findById(String id) {
        return roomRepository.findById(id).orElseThrow(() -> new HttpException("Room not found", HttpStatus.NOT_FOUND));
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
