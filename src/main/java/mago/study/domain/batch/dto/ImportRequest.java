package mago.study.domain.batch.dto;

import java.util.List;

public record ImportRequest(
        List<String> paths,
        Integer batchSize,
        Boolean removeHashtag,
        Boolean removeMention,
        Boolean removeEmoji
) {
}
