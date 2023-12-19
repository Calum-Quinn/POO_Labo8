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
            for (int i = 1; i < Math.abs(Math.max(xDiff,yDiff)); ++i) {
                if (board.getPieces()[fromX + i * xSign][fromY + i * ySign] != null) {
                    return false;
                }
            }

            // COMMENT L'ORDI SAIT SI ON CASTLE???

//            // Castle
//            // Checks:
//            // This piece has not moved
//            // The piece is either moving 2 spaces left or 3 right
//            // The king is in its starting position and has not moved (one check for each colour)
//            return !this.hasMoved() && (xDiff == -2 || xDiff == 3) && (color == PlayerColor.WHITE ? (board[4][0] instanceof King && !((King) board[4][0]).hasMoved()) : (board[4][7] instanceof King && !((King) board[4][7]).hasMoved()));
            return true;
        }
        return false;
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public String textValue() {
        return "Rook";
    }
}
