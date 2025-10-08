package ca.bytetube._17_chessgame;

class King extends Piece {

    public King(Color color) {
        super(color);
    }

    /**
     *每次只能移动1个square
     * 可以向8哥方向移动（上下左右，4个对角线）
     */
    @Override
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board) {
        if (!isWithinGrid(endRow, endCol)) return false;

        int rowMovement = Math.abs(endRow - startRow);
        int colMovement = Math.abs(endCol - startCol);

        if (rowMovement > 1 || colMovement > 1) {
            return false;
        }

        if (board[endRow][endCol].getPiece() != null && board[endRow][endCol].getPiece().getColor() == this.getColor()) {
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return (this.getColor() == Color.WHITE) ? "K" : "k";
    }

}
