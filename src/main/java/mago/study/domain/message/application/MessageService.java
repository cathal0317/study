package mago.study.domain.message.application;

import lombok.RequiredArgsConstructor;
import mago.study.domain.message.dao.MessageRepository;
import mago.study.domain.message.domain.MessageDocument;
import mago.study.domain.message.dto.req.MessageReqDto;
import mago.study.domain.message.dto.res.MessageGetDto;
import mago.study.domain.message.dto.res.MessageSlice;
import mago.study.domain.rag.application.RagService;
import mago.study.domain.room.dao.RoomRepository;
import mago.study.domain.room.domain.RoomDocument;
import mago.study.domain.user.domain.Role;
import mago.study.global.exception.custom.BusinessException;
import mago.study.global.exception.enums.ErrorCode;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MongoTemplate mongoTemplate;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;
    private final RagService ragService;

    public MessageGetDto sendMessage(ObjectId roomId, MessageReqDto messageReqDto) {
        RoomDocument roomDocument = roomRepository.findById(roomId).orElseThrow(
                () -> new BusinessException(ErrorCode.ROOM_NOT_FOUND)
        );
        messageRepository.save(MessageDocument.of(messageReqDto, roomId, Role.USER));
        return ragService.generateAnswer(roomDocument.getCharacter(), messageReqDto);
    }

    public MessageSlice getMessageHistory(ObjectId roomId, String before, int limit) {
        Criteria criteria = Criteria.where("roomId").is(roomId);

        // 커서(before)가 있으면 그 이전(_id lt)만
        if (before != null && !before.isBlank() && org.bson.types.ObjectId.isValid(before)) {
            criteria = criteria.and("_id").lt(new ObjectId(before));
        }

        Query query = new Query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "_id"))
                .limit(limit + 1); // hasMore 판단용으로 +1

        List<MessageGetDto> list = mongoTemplate.find(query, MessageDocument.class)
                .stream()
                .map(MessageGetDto::from)
                .toList();

        boolean hasMore = list.size() > limit;
        if (hasMore) {
            list = list.subList(0, limit);
        }

        String nextCursor = list.isEmpty() ? null : list.getLast().messageId();

        return new MessageSlice(list, nextCursor, hasMore);
    }
}
