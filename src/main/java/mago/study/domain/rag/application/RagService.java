package mago.study.domain.rag.application;

import lombok.RequiredArgsConstructor;
import mago.study.domain.message.dto.req.MessageReqDto;
import mago.study.domain.message.dto.res.MessageGetDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RagService {

    public MessageGetDto generateAnswer(String character, MessageReqDto messageReqDto) {
        //TODO RAG 연동
        return MessageGetDto.mock();
    }
}
