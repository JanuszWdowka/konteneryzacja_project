package dev.janusz.wdowka.movies;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static dev.janusz.wdowka.movies.TmbdStatic.TMDB_API_KEY;

/**
 * Klasa wykonująca połącznia z TMBD API
 */
public class TmbdApiService {

    /**
     * Pobranie danych o filmie na podstawie jego filmu
     * @param name pełna nazwa filmu
     * @return informacje o filmie
     * @throws IOException
     * @throws InterruptedException
     */
    public String getMovieByName(String name) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + name))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Pobranie danych o filmie na podstawie TMBD Id filmu
     * @param id Id filmu
     * @return dane o filmie
     * @throws IOException
     * @throws InterruptedException
     */
    public String getMovieById(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + id))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Pobiera dane o zdjęciach filmu
     * @param id TMBD Id filmu
     * @return dane o zdjęciach filmu
     * @throws IOException
     * @throws InterruptedException
     */
    public String getMoviesImages(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + id + "/images"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Pobieran dane o wideo filmu na podstawie TMBD Id filmu
     * @param id TMBD Id filmu
     * @return dane o wideo filmu
     * @throws IOException
     * @throws InterruptedException
     */
    public String getMoviesVideos(String id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + id + "/videos?language=en-US"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + TMDB_API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
