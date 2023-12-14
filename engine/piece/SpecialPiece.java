package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class SpecialPiece extends Piece{
    public SpecialPiece(PlayerColor color, PieceType type, Piece[][] board) {
        super(color, type, board);
    }

    public boolean hasMoved() {
        return false;
    }
}
