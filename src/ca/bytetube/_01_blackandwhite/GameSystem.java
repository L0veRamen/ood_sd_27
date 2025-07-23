package ca.bytetube._01_blackandwhite;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameSystem {
    private Grid grid;
    private Player[] players;
    private Map<String, Integer> score;
    private int targetScore;
    private int connectN;

    Scanner input = new Scanner(System.in);

    public GameSystem(Grid grid, int targetScore, int connectN) {
        this.grid = grid;
        this.targetScore = targetScore;
        this.connectN = connectN;
        players = new Player[]{
                new Player("Player 1", GridPosition.BLACK),
                new Player("Player 2", GridPosition.WHITE)
        };
        score = new HashMap<>();
        for (Player player : players) {
            this.score.put(player.getName(), 0);
        }
    }

    public void play() {
        int maxScore = 0;
        Player winner = null;
        while (maxScore < targetScore) {
            winner = playRound();
            System.out.println(winner.getName() + " won the round");
            maxScore = Math.max(score.get(winner.getName()), maxScore);

            grid.initBoard();
        }

        System.out.println("winner is " + winner.getName());
    }

    /**
     * Print the current board state to the console.
     */
    private void printBoard() {
        System.out.println("Board:");
        int[][] grid = this.grid.getGrids();
        for (int i = 0; i < grid.length; i++) {
            String row = "";
            for (int piece : grid[i]) {
                if (piece == GridPosition.EMPTY.ordinal()) {
                    row += "0 ";
                } else if (piece == GridPosition.BLACK.ordinal()) {
                    row += "B ";
                } else if (piece == GridPosition.WHITE.ordinal()) {
                    row += "W ";
                }
            }
            System.out.println(row);
        }
        System.out.println();
    }

    /**
     * Let the specified player make a move and return the move position.
     *
     * @param player The player who is making the move
     * @return Array containing the row and column of the move
     */
    private int[] playMove(Player player) {
        printBoard();
        System.out.println(player.getName() + "'s turn");
        int colNum = grid.getCols();
        System.out.println("enter col 0 to " + (colNum - 1) + " to add piece:");
        int moveCol = input.nextInt();
        int movRow = grid.placePiece(moveCol, player.getPiece());
        return new int[]{movRow, moveCol};
    }

    /**
     * Play one round: two players take turns until one wins.
     *
     * @return The player who wins the round
     */
    private Player playRound() {
        while (true) {
            for (Player player : players) {
                int[] pos = playMove(player);
                int row = pos[0];
                int col = pos[1];
                GridPosition pieceColor = player.getPiece();
                if (this.grid.checkWinCondition(connectN, row, col, pieceColor)) {
                    score.put(player.getName(), this.score.get(player.getName()) + 1);
                    return player;
                }
            }
        }
    }
}
