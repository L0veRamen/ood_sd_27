package ca.bytetube._17_chessgame;

class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    /**
     *只能沿对角线移动
     *不能跳过其他棋子
     * 可以吃掉对角线上对方的棋子
     */
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board) {
        return MovementUtil.isValidDiagonalMove(startRow, startCol, endRow, endCol, this.getColor(), board);
    }

    @Override
    public String getSymbol() {
        return (this.getColor() == Color.WHITE) ? "B" : "b";
    }

}
