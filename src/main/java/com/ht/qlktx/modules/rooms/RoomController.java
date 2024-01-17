package com.ht.qlktx.modules.rooms;

import com.ht.qlktx.entities.Room;
import com.ht.qlktx.modules.rooms.dtos.CreateRoomDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> create(@Valid @RequestBody CreateRoomDto createRoomDto) {
        var room = roomService.create(createRoomDto);
        return ResponseEntity.created(null).body(room);
    }

    @GetMapping
    public ResponseEntity<Iterable<Room>> findAll() {
        var rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable String id) {
        var room = roomService.findById(id);
        return ResponseEntity.ok(room);
    }
}
