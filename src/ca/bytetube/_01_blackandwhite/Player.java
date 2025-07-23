package ca.bytetube._01_blackandwhite;

public class Player {
    private String name;
    private GridPosition piece;

    public Player(String name, GridPosition piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return name;
    }


    public GridPosition getPiece() {
        return piece;
    }


}
