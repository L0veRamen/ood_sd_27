package ca.bytetube._17_chessgame;

class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    /**
     *可以8个方向
     * 移动距离不限
     */
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board) {
        return MovementUtil.isValidStraightMove(startRow, startCol, endRow, endCol, this.getColor(), board) || MovementUtil.isValidDiagonalMove(startRow, startCol, endRow, endCol, this.color, board);
    }

    @Override
    public String getSymbol() {
        return (this.getColor() == Color.WHITE) ? "Q" : "q";
    }

}
