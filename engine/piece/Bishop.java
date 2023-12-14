package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Bishop extends Piece {
    public Bishop(PlayerColor color, Piece[][] board) {
        super(color, PieceType.BISHOP, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {
        return false;
    }
}
