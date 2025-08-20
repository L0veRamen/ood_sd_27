package ca.bytetube._08_moviebooking;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String id;
    private String name;
    private int duration;  // mins
    private List<Show> shows;

    public Movie(String id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.shows = new ArrayList<>();
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
