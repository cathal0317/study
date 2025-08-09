package mago.study.domain.room.api;

import lombok.RequiredArgsConstructor;
import mago.study.domain.room.application.RoomService;
import mago.study.domain.room.dto.RoomAddDto;
import mago.study.domain.room.dto.RoomGetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody RoomAddDto roomAddDto) {
        String roomId = roomService.createRoom(roomAddDto);
        return ResponseEntity.ok(roomId);
    }

    @GetMapping
    public ResponseEntity<List<RoomGetDto>> getAll() {
        List<RoomGetDto> roomList = roomService.getAll();
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomGetDto> getRoom(
            @PathVariable String roomId
    ) {
        RoomGetDto roomGetDto = roomService.getRoom(roomId);
        return ResponseEntity.ok(roomGetDto);
    }

    @PostMapping("/{roomId}/reset")
    public ResponseEntity<String> resetRoom(
            @PathVariable String roomId
    ) {
        roomService.resetRoom(roomId);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable String roomId
    ){
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
