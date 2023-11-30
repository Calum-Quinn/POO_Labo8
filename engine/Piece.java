package engine;

import chess.PieceType;
import chess.PlayerColor;

public class Piece {
    private Board board;

    protected PlayerColor color;
    protected PieceType type;

    protected int x, y;

    Piece(PlayerColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }
}
