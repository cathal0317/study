package mago.study.domain.room.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mago.study.domain.room.dto.RoomAddDto;
import mago.study.global.entity.BaseDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "rooms")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDocument extends BaseDocument {
    @Id
    private String id;
    private String character;

    public static RoomDocument of(RoomAddDto roomAddDto) {
        return RoomDocument.builder()
                .character(roomAddDto.character())
                .build();
    }
}
