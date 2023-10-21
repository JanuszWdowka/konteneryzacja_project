package dev.janusz.wdowka.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * interfejs umożliwający komunikację z bazą do operacji CRUD danych
 */
@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
