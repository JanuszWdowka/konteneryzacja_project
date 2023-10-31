package dev.janusz.wdowka.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * interfejs umożliwający komunikację z bazą do operacji CRUD danych
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findMovieByImdbId(String imdbId);
    Optional<Movie> findMovieByTitle(String title);
}
