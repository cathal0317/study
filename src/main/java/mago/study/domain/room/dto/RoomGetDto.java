package mago.study.domain.room.dto;

import lombok.Builder;
import mago.study.domain.room.domain.RoomDocument;

import java.time.LocalDateTime;

@Builder
public record RoomGetDto(
        String roomId,
        String character,
        LocalDateTime createdAt,
        Long messageCount

) {

    public static RoomGetDto from(RoomDocument roomDocument){
        return RoomGetDto.builder()
                .roomId(roomDocument.getId())
                .character(roomDocument.getCharacter())
                .createdAt(roomDocument.getCreateAt())
                .messageCount(roomDocument.getMessageCount())
                .build();
    }
}
