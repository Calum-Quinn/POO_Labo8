package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece {
    public Queen(PlayerColor color, Position position) {
        super(color, PieceType.QUEEN, position);
    }
}
