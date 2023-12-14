package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Queen extends Piece {
    public Queen(PlayerColor color, Piece[][] board) {
        super(color, PieceType.QUEEN, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {


        // ON REGARDE QUE CE SOIT LA MÊME CHOSE QUE BISHOP ET ROOK, JE SAIS PAS COMMENT APPELER LEUR VALIDMOVE DEPUIS LA

        // Check if diagonal move (e.g. bishop)
        if (Math.abs(fromX - toX) == Math.abs(fromY - toY)) {
            int xSign = toX - fromX >= 0 ? 1 : -1;
            int ySign = toY - fromY >= 0 ? 1 : -1;
            // Check no pieces in between
            for (int i = 1; i < Math.abs(fromX - toX); ++i) {
                if (board[fromX + i * xSign][fromY + i * ySign] != null) {
                    return false;
                }
            }
        }
        // Straight/normal (e.g. rook)
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if (Math.abs(xDiff) == 0 || Math.abs(yDiff) == 0) {
            int xSign = xDiff >= 0 ? xDiff == 0 ? 0 : 1 : -1;
            int ySign = yDiff >= 0 ? yDiff == 0 ? 0 : 1 : -1;
            // Check no pieces in between
            for (int i = 1; i < Math.abs(xDiff); ++i) {
                if (board[fromX + i * xSign][fromY + i * ySign] != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
