package ca.bytetube._07_bookreservation;

public class CD extends Resource {
    private int duration;
    private String artist;


    public CD(String id, String name, int quantity, String artist, int duration) {
        super(id, quantity, name);
        this.duration = duration;
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
