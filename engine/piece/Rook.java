package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Rook extends Piece {
    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK);
    }

    public boolean hasMoved() {
        return false;
    }

    @Override
    public boolean validMove() {
        return false;
    }
}
