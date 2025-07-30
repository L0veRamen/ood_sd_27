package ca.bytetube._03_movierecommendationsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingRegister {
    private List<Movie> movies;
    private List<User> users;
    private Map<Integer, List<Movie>> userMovies;
    //Map<movieId,Map<userId,MovieRating>>
    private Map<Integer, Map<Integer, MovieRating>> movieRatings;

    public RatingRegister() {
        movies = new ArrayList<>();
        users = new ArrayList<>();
        userMovies = new HashMap<>();
        movieRatings = new HashMap<>();
    }

    public void addRating(User user, Movie movie, MovieRating rating) {
        if (!movieRatings.containsKey(movie.getId())) {
            movieRatings.put(movie.getId(), new HashMap<>());
            movies.add(movie);
        }

        if (!userMovies.containsKey(user.getId())) {
            userMovies.put(user.getId(), new ArrayList<>());
            users.add(user);
        }

        userMovies.get(user.getId()).add(movie);
        movieRatings.get(movie.getId()).put(user.getId(), rating);

    }

    public double getAverageScore(Movie movie) {
        //new movie
        if (!movieRatings.containsKey(movie.getId())) {
            return MovieRating.NOT_RATED.ordinal();
        }
        Map<Integer, MovieRating> ratings = movieRatings.get(movie.getId());
        //old movie
        int sum = 0;
        for (MovieRating rating : ratings.values()) {
            sum += rating.ordinal();
        }
        return (double) sum / ratings.size();
    }

    public List<Movie> getMovies() {
        return movies;
    }


    public List<User> getUsers() {
        return users;
    }


    public List<Movie> getUserMovies(User user) {
        if (!userMovies.containsKey(user.getId())) {
            return new ArrayList<>();
        }
        return userMovies.get(user.getId());
    }


    public Map<Integer, MovieRating> getMovieRatings(Movie movie) {
        if (!movieRatings.containsKey(movie.getId())) {
            return new HashMap<>();
        }
        return movieRatings.get(movie.getId());

    }


}
