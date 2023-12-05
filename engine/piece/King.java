package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Position;

public class King extends Piece {
    public King(PlayerColor color, Position position) {
        super(color, PieceType.KING, position);
    }

    public boolean hasMoved() {
        return false;
    }
}
