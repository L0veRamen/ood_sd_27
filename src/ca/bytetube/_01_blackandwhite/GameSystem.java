package ca.bytetube._01_blackandwhite;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameSystem {
    private final Grid grid;
    private final Player[] players;
    private final Map<String, Integer> score;
    private final int targetScore;
    private final int connectN;
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
        input.nextLine(); // Consume the leftover newline character
        int movRow = grid.placePiece(moveCol, player.getPiece());
        return new int[]{movRow, moveCol};
    }

    /**
     * Play one round: two players take turns until one wins.
     *
     * @return The player who wins the round
     */
    private Player playRound() {
        // Reset players' undo counts
        for (Player player : players) {
            player.resetUndoCount();
        }
        int currentPlayerIndex = 0;

        while (true) {
            Player currentPlayer = players[currentPlayerIndex];
            int[] pos = playMove(currentPlayer);

            System.out.println();
            printBoard(); // Print the board after the move

            System.out.println("Type 'u' to take back your move (Undos remaining: " + currentPlayer.getUndoCount() + "). Press Enter to continue.");
            String command = input.nextLine();
            // Check if the player wants to undo their last move
            if (command.equalsIgnoreCase("u")) {
                if (currentPlayer.hasUndos()) {
                    grid.undoLastMove();
                    currentPlayer.useUndos();
                    System.out.println("Move undone.");
                    continue; // Skip to the next loop iteration, keeping the same player
                } else {
                    System.out.println("No undos left! Move stands.");
                }
            }

            int row = pos[0];
            int col = pos[1];
            GridPosition pieceColor = currentPlayer.getPiece();

            if (this.grid.checkWinCondition(connectN, row, col, pieceColor)) {
                score.put(currentPlayer.getName(), this.score.get(currentPlayer.getName()) + 1);
                return currentPlayer;
            }

            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }
}
