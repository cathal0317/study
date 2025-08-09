package mago.study.domain.room.application;


import lombok.RequiredArgsConstructor;
import mago.study.domain.room.dao.RoomRepository;
import mago.study.domain.room.domain.RoomDocument;
import mago.study.domain.room.dto.RoomAddDto;
import mago.study.domain.room.dto.RoomGetDto;
import mago.study.global.exception.custom.BusinessException;
import mago.study.global.exception.enums.ErrorCode;
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
        return all.stream()
                .map(RoomGetDto::from)
                .toList();
    }

    public RoomGetDto getRoom(String roomId) {
        RoomDocument room = roomRepository.findById(roomId).orElseThrow(
                () -> new BusinessException(roomId, "roomId", ErrorCode.ROOM_NOT_FOUND)
        );

        return RoomGetDto.from(room);
    }

    public void resetRoom(String roomId) {
        // TODO 채팅방 리셋 처리
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }
}
