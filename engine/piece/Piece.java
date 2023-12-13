package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class Piece {
    private PlayerColor color;
    private PieceType type;
    private Position position;

    public Piece(PlayerColor color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
