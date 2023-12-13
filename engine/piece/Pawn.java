package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends Piece {
    public Pawn(PlayerColor color, Position position) {
        super(color, PieceType.PAWN, position);
    }

    public boolean hasMoved() {
        return false;
    }

    public void promote(PieceType type) {

    }
}
