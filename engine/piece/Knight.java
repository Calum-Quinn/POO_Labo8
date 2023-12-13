package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Knight extends Piece {
    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean validMove() {
        return false;
    }
}
