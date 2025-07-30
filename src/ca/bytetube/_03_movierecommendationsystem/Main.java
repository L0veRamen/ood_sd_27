package ca.bytetube._03_movierecommendationsystem;

public class Main {

    public static void main(String[] args) {
        User user1 = new User(1, "User 1");
        User user2 = new User(2, "User 2");
        User user3 = new User(3, "User 3");

        Movie movie1 = new Movie(1, "Captain America");
        Movie movie2 = new Movie(2, "Iron Man");
        Movie movie3 = new Movie(3, "Spider Man");

        RatingRegister ratings = new RatingRegister();
        //user1 vs user2:  0 ///user1 vs user3  0
        ratings.addRating(user1, movie1, MovieRating.FIVE);
        ratings.addRating(user1, movie2, MovieRating.FOUR);
        ratings.addRating(user2, movie1, MovieRating.FIVE);
        ratings.addRating(user2, movie3, MovieRating.THREE);
        ratings.addRating(user3, movie2, MovieRating.TWO);
        ratings.addRating(user3, movie3, MovieRating.FOUR);
        RecommendationSystem recommender = new RecommendationSystem(ratings);

        System.out.println(recommender.recommendMovie(user1)); // Spider Man


    }
}
