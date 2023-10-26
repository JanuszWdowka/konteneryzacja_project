package dev.janusz.wdowka.movies;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, shape = JsonFormat.Shape.ARRAY)
    private List<String> genres;
    private String poster;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, shape = JsonFormat.Shape.ARRAY)
    private List<String> backdrops;
    @DocumentReference
    private List<Review> reviewIds;


    public Movie(
            String imdbId,
            String title,
            String releaseDate,
            String trailerLink,
            List<String> genres,
            String poster,
            List<String> backdrops
    ) {
        this.imdbId = imdbId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        this.genres = genres;
        this.poster = poster;
        this.backdrops = backdrops;
        this.reviewIds = new ArrayList<Review>();
    }
}
