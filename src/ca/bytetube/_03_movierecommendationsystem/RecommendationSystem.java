package ca.bytetube._03_movierecommendationsystem;

import java.util.Map;

public class RecommendationSystem {
    private RatingRegister ratingRegister;

    public RecommendationSystem(RatingRegister ratingRegister) {
        this.ratingRegister = ratingRegister;
    }

    public String recommendMovie(User user) {
        if (ratingRegister.getUserMovies(user).isEmpty()) {
            return recommendMovieNewUser();
        } else {
            return recommendMovieExistingUser(user);
        }

    }


    //recommend the movie with highest average score
    public String recommendMovieNewUser() {
        Movie bestMovie = null;
        double bestRating = 0;
        for (Movie movie : ratingRegister.getMovies()) {
            double rating = ratingRegister.getAverageScore(movie);
            if (rating > bestRating) {
                bestRating = rating;
                bestMovie = movie;
            }
        }
        return bestMovie != null ? bestMovie.getTitle() : null;
    }

    //recommend the highest rated movie from another user with similar interest
    public String recommendMovieExistingUser(User user) {
        Movie bestMovie = null;
        int similarityScore = Integer.MAX_VALUE;
        for (User reviewer : ratingRegister.getUsers()) {
            if (reviewer.getId() == user.getId()) {
                continue;
            }
            int score = similarityScore(user, reviewer);

            if (score < similarityScore) {
                similarityScore = score;
                Movie recommendMovie = recommendUnWatchMovie(user, reviewer);
                bestMovie = recommendMovie != null ? recommendMovie : bestMovie;
            }
        }


        return bestMovie != null ? bestMovie.getTitle() : null;
    }


    public int similarityScore(User user1, User user2) {
        int score = Integer.MAX_VALUE;
        for (Movie movie : ratingRegister.getUserMovies(user2)) {
            Map<Integer, MovieRating> ratings = ratingRegister.getMovieRatings(movie);
            //if user1 also rated the movie, add the difference in ratings
            if (ratings.containsKey(user1.getId())) {
                score = (score == Integer.MAX_VALUE) ? 0 : score;
                score += Math.abs(ratings.get(user1.getId()).ordinal() - ratings.get(user2.getId()).ordinal());
            }
        }
        return score;
    }

    private Movie recommendUnWatchMovie(User user, User reviewer) {
        Movie bestMovie = null;
        int betRating = 0;
        for (Movie movie : ratingRegister.getUserMovies(reviewer)) {
            Map<Integer, MovieRating> ratings = ratingRegister.getMovieRatings(movie);
            //if user has not watched the movie, and reviewer gave it a high rating,recommend it
            if (!ratings.containsKey(user.getId()) && ratings.get(reviewer.getId()).ordinal() > betRating) {
                bestMovie = movie;
                betRating = ratings.get(reviewer.getId()).ordinal();
            }
        }
        return bestMovie;
    }

}
