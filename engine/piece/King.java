package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class King extends Piece {
    public King(PlayerColor color) {
        super(color, PieceType.KING);
    }

    public boolean hasMoved() {
        return false;
    }

    @Override
    public boolean validMove() {
        return false;
    }
}
