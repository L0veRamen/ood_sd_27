package ca.bytetube._17_chessgame;

class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    /**
     *只能向前移动
     * 第一次移动可以移动1格或者2格
     */
    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol, Square[][] board) {
        if (!isWithinGrid(endRow, endCol)) return false;

        int rowMovement = endRow - startRow;
        int colMovement = endCol - startCol;

        int direction = (this.getColor() == Color.WHITE) ? -1 : 1;

        if (colMovement != 0) return false;

        if (rowMovement == direction && board[endRow][endCol].getPiece() == null) {
            return true;
        }
        //是否是第一次移动              //是否移动了2格
        if (isFirstMove(startRow) && rowMovement == (2 * direction) &&
                //中间的格子是否为空，                                        目标格子上是否没有棋子
                board[startRow + direction][startCol].getPiece() == null && board[endRow][endCol].getPiece() == null) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return (this.getColor() == Color.WHITE) ? "P" : "p";
    }

    private boolean isFirstMove(int startRow) {
        if (this.getColor() == Color.WHITE) {
            return startRow == 6;
        } else {
            return startRow == 1;
        }
    }

}
