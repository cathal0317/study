package mago.study.domain.room.dto;

import mago.study.domain.room.domain.RoomDocument;

public record RoomGetDto(
        String character
) {

    public static RoomGetDto from(RoomDocument roomDocument){
        return new RoomGetDto(roomDocument.getCharacter());
    }
}
