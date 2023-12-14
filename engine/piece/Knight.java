package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Knight extends Piece {
    public Knight(PlayerColor color, Piece[][] board) {
        super(color, PieceType.KNIGHT, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {
        return false;
    }
}
