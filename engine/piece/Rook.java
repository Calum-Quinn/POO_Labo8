package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Position;

public class Rook extends Piece {
    public Rook(PlayerColor color, Position position) {
        super(color, PieceType.ROOK, position);
    }

    public boolean hasMoved() {
        return false;
    }
}
