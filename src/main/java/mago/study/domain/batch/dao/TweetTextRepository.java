package mago.study.domain.batch.dao;

import mago.study.domain.batch.domain.TweetText;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetTextRepository extends MongoRepository<TweetText, String> {
}
