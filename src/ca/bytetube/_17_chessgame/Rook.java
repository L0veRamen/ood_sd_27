package ca.bytetube._17_chessgame;

class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    /**
     * 只能走直线（行或者列）
     * 移动距离不限
     */
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board) {
        return MovementUtil.isValidStraightMove(startRow, startCol, endRow, endCol, this.getColor(), board);
    }


    @Override
    public String getSymbol() {
        return (this.getColor() == Color.WHITE) ? "R" : "r";
    }

}
