package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece {
    public Queen(PlayerColor color, Piece[][] board) {
        super(color, PieceType.QUEEN, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {
        return false;
    }
}
