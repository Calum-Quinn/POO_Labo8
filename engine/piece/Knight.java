package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public class Knight extends Piece {
    public Knight(PlayerColor color, Board board) {
        super(color, PieceType.KNIGHT, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Board board, boolean capture) {
        return Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 2 || Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY) == 1;
    }

    public String textValue() {
        return "Knight";
    }
}
