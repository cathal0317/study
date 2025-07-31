package mago.study.domain.message.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mago.study.global.entity.BaseDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collation = "messages")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDocument extends BaseDocument {
    @Id
    private String id;
    private String roomId;
    private String content;
}
