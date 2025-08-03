package mago.study.domain.room.api;

import lombok.RequiredArgsConstructor;
import mago.study.domain.room.application.RoomService;
import mago.study.domain.room.domain.RoomDocument;
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


}
