package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class SpecialPiece extends Piece{

    protected boolean moved;

    public SpecialPiece(PlayerColor color, PieceType type, Piece[][] board) {
        super(color, type, board);
        moved = false;
    }

    public boolean hasMoved() {
        return moved;
    }
}
