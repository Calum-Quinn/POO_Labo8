package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends Piece {
    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN);
    }

    public boolean hasMoved() {
        return false;
    }

    public void promote(PieceType type) {

    }

    @Override
    public boolean validMove() {
        return false;
    }
}
