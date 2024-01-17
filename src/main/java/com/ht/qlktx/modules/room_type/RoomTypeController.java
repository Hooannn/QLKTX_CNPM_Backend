package com.ht.qlktx.modules.room_type;

import com.ht.qlktx.entities.RoomType;
import com.ht.qlktx.modules.room_type.dtos.CreateRoomTypeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/room-types")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<RoomType> create(@Valid @RequestBody CreateRoomTypeDto createRoomTypeDto) {
        var roomType = roomTypeService.create(createRoomTypeDto);
        return ResponseEntity.created(null).body(roomType);
    }

    @GetMapping
    public ResponseEntity<Iterable<RoomType>> findAll() {
        var roomTypes = roomTypeService.findAll();
        return ResponseEntity.ok(roomTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> findById(@PathVariable String id) {
        var roomType = roomTypeService.findById(id);
        return ResponseEntity.ok(roomType);
    }
}
