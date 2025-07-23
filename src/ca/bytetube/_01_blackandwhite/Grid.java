package ca.bytetube._01_blackandwhite;

public class Grid {
    private int[][] grids;
    private int rows;
    private int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initBoard();
    }

    public void initBoard() {
        this.grids = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grids[r][c] = GridPosition.EMPTY.ordinal();
            }
        }
    }

    //place one piece in the specified col, automatically falling to the lowest row
    /**
     * @return row index
     */
    public int placePiece(int col, GridPosition piece) {
        if (col < 0 || col >= cols) throw new IndexOutOfBoundsException("invalid col !");
        if (piece == GridPosition.EMPTY) throw new RuntimeException("Invalid piece");

        for (int r = rows - 1; r >= 0; r--) {
            if (grids[r][col] == GridPosition.EMPTY.ordinal()) {
                grids[r][col] = piece.ordinal();
                return r;
            }
        }
        return -1;
    }

    //check if the piece at specified position forms N in a row/col/diagonal
    public boolean checkWinCondition(int connectN, int row, int col, GridPosition piece) {
        //1.row
        int count = 0;
        for (int c = 0; c < cols; c++) {
            if (grids[row][c] == piece.ordinal()) {
                count++;
            } else {
                count--;
            }
            if (count == connectN) {
                return true;
            }
        }
        //2.col
        count = 0;
        for (int r = 0; r < rows; r++) {
            if (grids[r][col] == piece.ordinal()) {
                count++;
            } else {
                count--;
            }
            if (count == connectN) {
                return true;
            }
        }
        //3.diagonal
        count = 0;
        for (int r = 0; r < rows; r++) {
            int c = row + col - r;//row + col = r + c
            if (c >= 0 && c < cols && grids[r][c] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }

        count = 0;
        for (int r = 0; r < rows; r++) {
            int c = col - row + r;//row - col = r - c
            if (c >= 0 && c < cols && grids[r][c] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        return false;
    }

    public int[][] getGrids() {
        return grids;
    }


    public int getCols() {
        return cols;
    }


}
