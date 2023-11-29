package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Piece {
    private Board board;

    private PlayerColor color;
    private PieceType type;

    Piece(PlayerColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }
}
