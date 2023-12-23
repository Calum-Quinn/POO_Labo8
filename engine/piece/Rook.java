package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public class Rook extends SpecialPiece {
    public Rook(PlayerColor color, Board board) {
        super(color, PieceType.ROOK, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Board board, boolean capture) {
        // Straight/normal
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if (Math.abs(xDiff) == 0 || Math.abs(yDiff) == 0) {
            int xSign = xDiff >= 0 ? xDiff == 0 ? 0 : 1 : -1;
            int ySign = yDiff >= 0 ? yDiff == 0 ? 0 : 1 : -1;
            // Check no pieces in between
            for (int i = 1; i < Math.max(Math.abs(xDiff),Math.abs(yDiff)); ++i) {
                if (board.getPieces()[fromX + i * xSign][fromY + i * ySign] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String textValue() {
        return "Rook";
    }
}
