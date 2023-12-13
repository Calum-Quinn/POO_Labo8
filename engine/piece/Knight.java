package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Knight extends Piece {
    public Knight(PlayerColor color, Position position) {
        super(color, PieceType.KNIGHT, position);
    }
}
