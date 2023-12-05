package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Position;

public class Bishop extends Piece {
    public Bishop(PlayerColor color, Position position) {
        super(color, PieceType.BISHOP, position);
    }
}
