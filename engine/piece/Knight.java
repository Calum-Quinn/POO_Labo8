package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Position;

public class Knight extends Piece {
    public Knight(PlayerColor color, Position position) {
        super(color, PieceType.KNIGHT, position);
    }
}
