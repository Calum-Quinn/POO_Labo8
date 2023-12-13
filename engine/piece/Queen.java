package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece {
    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean validMove() {
        return false;
    }
}
