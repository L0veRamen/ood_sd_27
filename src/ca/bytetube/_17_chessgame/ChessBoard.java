package ca.bytetube._17_chessgame;

import java.util.Scanner;

class ChessBoard {
    private final Square[][] board = new Square[8][8];

    public ChessBoard() {
        initializeBoardAndPieces();
    }

    private void initializeBoardAndPieces() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Color squareColor = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;
                board[i][j] = new Square(squareColor);
            }
        }
        initializeBlackPieces();
        initializeWhitePieces();
    }

    private void initializeBlackPieces() {
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(Color.BLACK));
        }
        board[0][0].setPiece(new Rook(Color.BLACK));
        board[0][7].setPiece(new Rook(Color.BLACK));
        board[0][1].setPiece(new Knight(Color.BLACK));
        board[0][6].setPiece(new Knight(Color.BLACK));
        board[0][2].setPiece(new Bishop(Color.BLACK));
        board[0][5].setPiece(new Bishop(Color.BLACK));
        board[0][3].setPiece(new Queen(Color.BLACK));
        board[0][4].setPiece(new King(Color.BLACK));
    }

    private void initializeWhitePieces() {
        for (int i = 0; i < 8; i++) {
            board[6][i].setPiece(new Pawn(Color.WHITE));
        }
        board[7][0].setPiece(new Rook(Color.WHITE));
        board[7][7].setPiece(new Rook(Color.WHITE));
        board[7][1].setPiece(new Knight(Color.WHITE));
        board[7][6].setPiece(new Knight(Color.WHITE));
        board[7][2].setPiece(new Bishop(Color.WHITE));
        board[7][5].setPiece(new Bishop(Color.WHITE));
        board[7][3].setPiece(new Queen(Color.WHITE));
        board[7][4].setPiece(new King(Color.WHITE));
    }

    public boolean movePiece(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int startRow = promptInput(scanner, "Enter starting row: ");
            int startCol = promptInput(scanner, "Enter starting column: ");
            int endRow = promptInput(scanner, "Enter destination row: ");
            int endCol = promptInput(scanner, "Enter destination column: ");

            if (!Piece.isWithinGrid(endRow, endCol)) {
                return false;
            }

            Piece pieceToMove = this.board[startRow][startCol].getPiece();

            if (pieceToMove == null) {
                System.out.println("There's no piece at the specified starting position.");
                continue;
            }

            if (pieceToMove.getColor() != currentPlayer.getColor()) {
                System.out.println("It's not your turn to move this piece.");
                continue;
            }

            if (pieceToMove.isValidMove(startRow, startCol, endRow, endCol, this.board)) {

                Piece destinationPiece = this.board[endRow][endCol].getPiece();
                if (destinationPiece != null && destinationPiece.getColor() != pieceToMove.getColor()) {
                    this.board[endRow][endCol].setPiece(null);//吃掉对方棋子
                }
                //将新棋子放到新位置上
                this.board[endRow][endCol].setPiece(pieceToMove);
                this.board[startRow][startCol].setPiece(null);
                System.out.println(pieceToMove.getSymbol() + " moved to " + endRow + ", " + endCol);
                return true;
            } else {
                System.out.println("Invalid move for the " + pieceToMove.getSymbol() + ". Please try again.");
            }
        }
    }

    private int promptInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    public void displayBoard() {
        System.out.println("  0 1 2 3 4 5 6 7");
        System.out.println("  ---------------");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    System.out.print(board[i][j].getPiece().getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

}

