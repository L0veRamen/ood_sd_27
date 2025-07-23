package ca.bytetube._01_blackandwhite;

import java.util.Stack;

public class Grid {
    private int[][] grids;
    private final int rows;
    private final int cols;

    private Stack<int[]> gameHistory; // store[row, col] of each move


    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.gameHistory = new Stack<>();
        initBoard();
    }

    public void initBoard() {
        this.grids = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grids[r][c] = GridPosition.EMPTY.ordinal();
            }
        }
        this.gameHistory.clear(); // clear game history for a new game
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
                gameHistory.push(new int[]{r, col}); // record the move
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
                count = 0;
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
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        //3.diagonal(top right to bottom left)
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
        //4.diagonal(top left to bottom right)
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

    public boolean undoLastMove() {
        if (gameHistory.isEmpty()) {
            return false; // no moves to undo
        }
        int[] lastMove = gameHistory.pop();
        int row = lastMove[0];
        int col = lastMove[1];
        grids[row][col] = GridPosition.EMPTY.ordinal(); // reset the cell to empty
        return true; // undo successful
    }


}
