package ca.bytetube._17_chessgame;

class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    /**
     *移动形状是"L"形状
     * 唯一一个可以跳过其他棋子的棋子
     */
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board) {
        if (!isWithinGrid(endRow, endCol)) return false;

        int rowMovement = Math.abs(endRow - startRow);
        int colMovement = Math.abs(endCol - startCol);


        if (rowMovement == 2 && colMovement == 1 || rowMovement == 1 && colMovement == 2) {


            if (board[endRow][endCol].getPiece() != null && board[endRow][endCol].getPiece().getColor() == this.getColor()) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return (this.getColor() == Color.WHITE) ? "N" : "n";
    }

}
