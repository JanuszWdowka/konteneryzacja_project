package dev.janusz.wdowka.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Api Controller
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * Pobiera jeden film na podstawie Id filmu
     * @param id Id filmu, który chcemy pobrać
     * @return json pobranego filmu
     */
    @GetMapping("getById/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Movie>>(movieService.oneMovieById(id), HttpStatus.OK);
    }

    /**
     * Pobieranie wszystkich danych
     * @return json z danymi
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    /**
     * Pobiera jeden film na podstawie imdb Id
     * @param imdbId Id filmu, który chcemy pobrać
     * @return json pobranego filmu
     */
    @GetMapping("getByImdbId/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovieByImdbId(@PathVariable String imdbId) {
        return new ResponseEntity<Optional<Movie>>(movieService.oneMovieByImdbId(imdbId), HttpStatus.OK);
    }
}
