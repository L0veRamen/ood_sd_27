import ca.bytetube._01_blackandwhite.GameSystem;
import ca.bytetube._01_blackandwhite.Grid;


public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(15, 15);
        GameSystem game = new GameSystem(grid, 3, 5);
        game.play();
    }
}