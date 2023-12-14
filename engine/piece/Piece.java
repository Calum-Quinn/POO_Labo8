package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public abstract class Piece {
    protected PlayerColor color;

    protected PieceType type;

    protected Piece[][] board;

    public Piece(PlayerColor color, PieceType type, Piece[][] board) {
        this.color = color;
        this.type = type;
        this.board = board;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public abstract boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture);
}
