package mago.study.domain.message.dto.res;

import lombok.Builder;
import mago.study.domain.message.domain.MessageDocument;
import mago.study.domain.user.domain.Role;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Builder
public record MessageGetDto(
        ObjectId messageId,
        Role role,
        String content,
        LocalDateTime createdAt
) {

    public static MessageGetDto from(MessageDocument message) {
        return MessageGetDto.builder()
                .messageId(message.getId())
                .role(message.getRole())
                .content(message.getContent())
                .createdAt(message.getCreateAt())
                .build();
    }

    // TODO RAG 연동 후 삭제
    public static MessageGetDto mock() {
        return MessageGetDto.builder()
                .messageId("mockId")
                .role(Role.ASSISTANT)
                .content("mock")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
