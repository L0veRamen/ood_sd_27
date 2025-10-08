package ca.bytetube._17_chessgame;

class ChessGame {
    private ChessBoard board;
    private Player whitePlayer;
    private Player blackPlayer;

    public ChessGame() {
        this.board = new ChessBoard();
        this.whitePlayer = new Player(Color.WHITE);
        this.blackPlayer = new Player(Color.BLACK);
    }

    public void startGame() {
        System.out.println("Welcome to Chess, UPPERCASE denotes white, LOWERCASE denotes black");
        board.displayBoard();

        Player currentPlayer = whitePlayer;

        while (true) {
            System.out.println("Current turn: " + (currentPlayer.getColor() == Color.WHITE ? "White" : "Black"));

            boolean moveSuccessful = board.movePiece(currentPlayer);
            if (moveSuccessful) {
                board.displayBoard();
                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

}

