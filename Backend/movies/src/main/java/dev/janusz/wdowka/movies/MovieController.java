package dev.janusz.wdowka.movies;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Api Controller obiektu Movie
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("getByTitle/{title}")
    public ResponseEntity<Optional<Movie>> getMovieByTitle(@PathVariable String title) {
        return new ResponseEntity<Optional<Movie>>(movieService.oneMovieByTitle(title), HttpStatus.OK);
    }

    /**
     * Tworzy film w bazie danych
     * @param payload ciało tranzakcji
     * @return json z danymi
     */
    @PostMapping("addMovie")
    public ResponseEntity<Movie> createMovie(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
        List<String> genresList = convertToListOfStrings(payload.get("genres"));
        List<String> backdropsList = convertToListOfStrings(payload.get("backdrops"));
        return new ResponseEntity<Movie>(
                movieService.createMovie(
                        payload.get("imdbId").toString(),
                        payload.get("title").toString(),
                        payload.get("releaseDate").toString(),
                        payload.get("trailerLink").toString(),
                        genresList,
                        payload.get("poster").toString(),
                        backdropsList
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * Funkcja do konwersji na listę Stringów listy z jsona z frontu
     * @param value prowadzona wartość z JSONa
     * @return Lista Stringów
     */
    private List<String> convertToListOfStrings(Object value) {
        List<String> result = new ArrayList<>();
        if (value instanceof List) {
            for (Object item : (List<?>) value) {
                if (item != null) {
                    result.add(item.toString());
                }
            }
        } else if (value != null) {
            result.add(value.toString());
        }
        return result;
    }
}
