package dev.janusz.wdowka.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Klasa Service dla obiektu Movie
 */
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    /**
     * Pobiera z bazy film na podstawie Id
     * @param id Id filmu
     * @return pobrany film
     */
    public Optional<Movie> oneMovieById(ObjectId id) {
        return movieRepository.findById(id);
    }

    /**
     * pobiera wszystkie dane z Mongo
     * @return lista filmów
     */
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    /**
     * Pobiera z bazy film na podstawie imdb Id
     * @param imdbId imdbId filmu
     * @return pobrany film
     */
    public Optional<Movie> oneMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }

    public Optional<Movie> oneMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    /**
     * Funkcja dodająca film do bazy danych
     * @param imdbId IMDBID filmu
     * @param title Tytuł filmu
     * @param releaseDate Data premiery filmu
     * @param trailerLink link do trailera filmu
     * @param genres gatunek filmu
     * @param poster plakat filmu
     * @param backdrops zdjęcia filmu
     * @return nowy stworzonu film
     */
    public Movie createMovie(
            String imdbId,
            String title,
            String releaseDate,
            String trailerLink,
            List<String> genres,
            String poster,
            List<String> backdrops
            ) {
        return movieRepository.insert(new Movie(imdbId, title, releaseDate, trailerLink, genres, poster, backdrops));
    }
}
