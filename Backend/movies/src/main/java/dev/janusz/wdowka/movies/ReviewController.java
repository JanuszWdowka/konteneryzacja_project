package dev.janusz.wdowka.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Api Controller obiektu Review
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /**
     * Wywołanie tworzenia nowej recenzji
     * @param payload ciało tranzakcji
     * @return json z danymi
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(
                reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")),
                HttpStatus.CREATED
        );
    }
}
