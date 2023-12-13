package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class Piece {
    private PlayerColor color;
    private PieceType type;

    public Piece(PlayerColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }
}
