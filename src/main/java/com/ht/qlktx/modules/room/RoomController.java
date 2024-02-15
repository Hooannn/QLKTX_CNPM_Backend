package com.ht.qlktx.modules.room;

import com.ht.qlktx.annotations.RequiredRole;
import com.ht.qlktx.config.Response;
import com.ht.qlktx.entities.Room;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.room.dtos.CreateRoomDto;
import com.ht.qlktx.modules.room.dtos.UpdateRoomDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<Room>> create(@Valid @RequestBody CreateRoomDto createRoomDto) {
        var room = roomService.create(createRoomDto);
        return ResponseEntity.created(null).body(new Response<>(
                HttpStatus.CREATED.value(),
                "Phòng đã được tạo thành công",
                room
        ));
    }

    @DeleteMapping("/{roomId}")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<String>> delete(@PathVariable String roomId) {
        roomService.delete(roomId);
        return ResponseEntity.ok().body(new Response<>(
                HttpStatus.OK.value(),
                "Phòng đã được xóa thành công",
                roomId
        ));
    }

    @PutMapping("/{roomId}")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<Room>> update(@PathVariable String roomId, @Valid @RequestBody UpdateRoomDto updateRoomDto) {
        var room = roomService.update(roomId, updateRoomDto);
        return ResponseEntity.ok().body(new Response<>(
                HttpStatus.OK.value(),
                "Phòng đã được cập nhật thành công",
                room
        ));
    }

    @GetMapping
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<Iterable<Room>>> findAll() {
        var rooms = roomService.findAll();
        return ResponseEntity.ok().body(new Response<>(
                HttpStatus.OK.value(),
                "Danh sách phòng",
                rooms
        ));
    }

    @GetMapping("/{roomId}")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<Room>> findById(@PathVariable String roomId) {
        var room = roomService.findById(roomId);
        return ResponseEntity.ok().body(new Response<>(
                HttpStatus.OK.value(),
                "Phòng",
                room
        ));
    }

    @GetMapping("/lookup")
    @RequiredRole({Role.STAFF})
    public ResponseEntity<Response<Iterable<Room>>> lookUpById(@RequestParam String keyword) {
        var rooms = roomService.lookUpById(keyword);
        return ResponseEntity.ok().body(new Response<>(
                HttpStatus.OK.value(),
                "Danh sách phòng",
                rooms
        ));
    }
}
