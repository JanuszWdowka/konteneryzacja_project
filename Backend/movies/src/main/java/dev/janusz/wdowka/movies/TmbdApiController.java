package dev.janusz.wdowka.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Klasa do pobierania danych o filmie za pomocą api TMBD
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/api")
public class TmbdApiController {
    TmbdApiService tmbdApiService = new TmbdApiService();

    /**
     *
     * @param name pełna nazwa filmu
     * @return dane o filmie
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("getByName/{name}")
    public ResponseEntity<String> getMovieByName(@PathVariable String name) throws IOException, InterruptedException {
        return new ResponseEntity<String>(tmbdApiService.getMovieByName(name), HttpStatus.OK);
    }

    /**
     *
     * @param id TMBD id filmu
     * @return dane o filmie
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("getById/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable String id) throws IOException, InterruptedException {
        return new ResponseEntity<String>(tmbdApiService.getMovieById(id), HttpStatus.OK);
    }

    /**
     *
     * @param id TMBD id filmu
     * @return dane o zdjęciach filmu
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("getImagesById/{id}")
    public ResponseEntity<String> getMovieImagesById(@PathVariable String id) throws IOException, InterruptedException {
        return new ResponseEntity<String>(tmbdApiService.getMoviesImages(id), HttpStatus.OK);
    }

    /**
     *
     * @param id TMBD id filmu
     * @return dane o wideo filmu
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("getVideosById/{id}")
    public ResponseEntity<String> getMovieVideosById(@PathVariable String id) throws IOException, InterruptedException {
        return new ResponseEntity<String>(tmbdApiService.getMoviesVideos(id), HttpStatus.OK);
    }
}
