package ca.bytetube._03_movierecommendationsystem;

public class Movie {
    private int id;
    private String title;

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return id == movie.id;  // Compare by ID, not object reference
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
