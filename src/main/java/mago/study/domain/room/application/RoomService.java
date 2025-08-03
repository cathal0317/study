package mago.study.domain.room.application;


import lombok.RequiredArgsConstructor;
import mago.study.domain.room.dao.RoomRepository;
import mago.study.domain.room.domain.RoomDocument;
import mago.study.domain.room.dto.RoomAddDto;
import mago.study.domain.room.dto.RoomGetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public String createRoom(RoomAddDto roomAddDto) {
        RoomDocument roomDocument = roomRepository.save(RoomDocument.of(roomAddDto));
        return roomDocument.getId();
    }

    public List<RoomGetDto> getAll() {
        List<RoomDocument> all = roomRepository.findAll();
        List<RoomGetDto> roomlist = all.stream()
                .map(RoomGetDto::from)
                .toList();
        return roomlist;
    }
}
