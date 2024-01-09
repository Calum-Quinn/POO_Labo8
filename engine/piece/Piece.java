package engine.piece;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public abstract class Piece implements ChessView.UserChoice {
    protected PlayerColor color;

    protected PieceType type;

    protected Board board;

    public Piece(PlayerColor color, PieceType type, Board board) {
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

    /**
     * Check's whether a specific move is valid for the piece currently on the departure square.
     *
     * @param fromX   Starting x coordinate.
     * @param fromY   Starting y coordinate.
     * @param toX     Desired x coordinate.
     * @param toY     Desired y coordinate.
     * @param board   Game board to analyse.
     * @param capture Piece on the destination square.
     * @return Valid move.
     */
    public abstract boolean validMove(int fromX, int fromY, int toX, int toY, Board board, boolean capture);

    /**
     * Provides a name for each type of piece for the graphic interface.
     *
     * @return Piece type name.
     */
    public abstract String textValue();
}
