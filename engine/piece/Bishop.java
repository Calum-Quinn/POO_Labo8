package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public class Bishop extends Piece {
    public Bishop(PlayerColor color, Board board) {
        super(color, PieceType.BISHOP, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Board board, boolean capture) {
        // Check if diagonal move
        if (Math.abs(fromX - toX) == Math.abs(fromY - toY)) {
            int xSign = toX - fromX >= 0 ? 1 : -1;
            int ySign = toY - fromY >= 0 ? 1 : -1;
            // Check no pieces in between
            for (int i = 1; i < Math.abs(fromX - toX); ++i) {
                if (board.getPieces()[fromX + i * xSign][fromY + i * ySign] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String textValue() {
        return "Bishop";
    }
}
