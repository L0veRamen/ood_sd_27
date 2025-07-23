package ca.bytetube._01_blackandwhite;

public class Player {
    private static final int MAX_UNDO_COUNT = 3;
    private final String name;
    private final GridPosition piece;
    private int undoCount;

    public Player(String name, GridPosition piece) {
        this.name = name;
        this.piece = piece;
        this.undoCount = MAX_UNDO_COUNT;
    }

    public String getName() {
        return name;
    }

    public GridPosition getPiece() {
        return piece;
    }

    public boolean hasUndos() {
        return undoCount > 0;
    }

    /**
     * Use one Undo move.
     * @return true if successful, false if no undos left.
     */
    public boolean useUndos() {
        if (hasUndos()) {
            this.undoCount--;
            return true;
        }
        return false;
    }

    public int getUndoCount() {
        return undoCount;
    }

    public void resetUndoCount() {
        this.undoCount = MAX_UNDO_COUNT;
    }


}
