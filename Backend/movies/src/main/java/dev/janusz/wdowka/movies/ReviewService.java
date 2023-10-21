package dev.janusz.wdowka.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * Klasa Service dla obiektu Review
 */
@Service
public class ReviewService {
    @Autowired
    private  ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Funkcja tworząca recenzję filmu
     * @param reviewBody tekst recenzji
     * @param imdbId id filmu
     * @return nowo stworzona recenzja
     */
    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody));
        mongoTemplate.update(Movie.class)
                .matching(
                        Criteria.where("imdbId")
                                .is(imdbId)
                )
                .apply(new Update()
                        .push("reviewIds")
                        .value(review)
                )
                .first();
        return review;
    }
}
