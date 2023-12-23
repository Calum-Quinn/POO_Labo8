package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public abstract class SpecialPiece extends Piece{

    protected boolean moved;

    public SpecialPiece(PlayerColor color, PieceType type, Board board) {
        super(color, type, board);
        moved = false;
    }

    /**
     * Check's whether a piece has been moved before.
     *
     * @return  Piece has not been moved.
     */
    public boolean hasNotMoved() {
        return !moved;
    }

    /**
     * Informs that the piece has been moved.
     */
    public void moved() {
        moved = true;
    }
}
