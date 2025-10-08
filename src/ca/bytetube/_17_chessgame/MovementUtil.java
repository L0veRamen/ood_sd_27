package ca.bytetube._17_chessgame;

class MovementUtil {

    public static boolean isValidStraightMove(int startRow, int startCol, int endRow, int endCol, Color color, Square[][] board) {
        if (!Piece.isWithinGrid(endRow, endCol)) return false;


        int rowMovement = Math.abs(endRow - startRow);
        int colMovement = Math.abs(endCol - startCol);


        if ((rowMovement != 0 && colMovement != 0) || (rowMovement == 0 && colMovement == 0)) {
            return false;
        } else {

            int rowIncrement = (endRow > startRow) ? 1 : -1;
            int colIncrement = (endCol > startCol) ? 1 : -1;

            if (rowMovement == 0) {
                int y = startCol + colIncrement;
                while (y != endCol) {
                    if (board[startRow][y].getPiece() != null) {
                        return false;
                    }
                    y += colIncrement;
                }
            } else {  // Moving vertically
                int x = startRow + rowIncrement;
                while (x != endRow) {
                    if (board[x][startCol].getPiece() != null) {
                        return false;
                    }
                    x += rowIncrement;
                }
            }


            if (board[endRow][endCol].getPiece() != null && board[endRow][endCol].getPiece().getColor() == color) {
                return false;
            }

            return true;
        }
    }

    public static boolean isValidDiagonalMove(int startRow, int startCol, int endRow, int endCol, Color color, Square[][] board) {
        if (!Piece.isWithinGrid(endRow, endCol)) return false;

        int rowMovement = Math.abs(endRow - startRow);
        int colMovement = Math.abs(endCol - startCol);
        //如果是水平或者垂直的移动，则不算对角线移动
        if (rowMovement == 0 || colMovement == 0) return false;

        //检查是否是真正的对角线移动（行列移动距离必须相等）
        if (rowMovement == colMovement) {
            int rowIncrement = (endRow > startRow) ? 1 : -1;
            int colIncrement = (endCol > startCol) ? 1 : -1;


            int x = startRow + rowIncrement;
            int y = startCol + colIncrement;

            //检查对角线上所有的square
            while (x != endRow && y != endCol) {

                if (board[x][y].getPiece() != null) {

                    if (board[x][y].getPiece().getColor() == color) {
                        return false;
                    }
                    return false;

                }

                x += rowIncrement;
                y += colIncrement;
            }
            // 8. 检查目标位置是否有己方棋子
            if (board[endRow][endCol].getPiece() != null && board[endRow][endCol].getPiece().getColor() == color) {
                return false;
            }
            return true;

        } else {
            return false;// 不是对角线移动
        }
    }

}
