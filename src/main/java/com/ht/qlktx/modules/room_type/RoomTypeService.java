package com.ht.qlktx.modules.room_type;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.RoomType;
import com.ht.qlktx.modules.room_type.dtos.CreateRoomTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public RoomType create(CreateRoomTypeDto createRoomTypeDto) {
        var roomType = RoomType.builder()
                .name(createRoomTypeDto.getName())
                .price(createRoomTypeDto.getPrice())
                .maxPeople(createRoomTypeDto.getMaxPeople())
                .build();

        return roomTypeRepository.save(roomType);
    }

    public RoomType findById(String id) {
        return roomTypeRepository.findById(Long.valueOf(id)).orElseThrow(() -> new HttpException("Room type not found", HttpStatus.NOT_FOUND));
    }

    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }
}
