package ca.bytetube._17_chessgame;

abstract class Piece {
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    protected Color getColor() {
        return this.color;
    }

    public static boolean isWithinGrid(int endRow, int endCol) {
        if (endRow > 7 || endRow < 0 || endCol > 7 || endCol < 0) return false;
        return true;
    }

    public abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board);

    protected abstract String getSymbol();

}
