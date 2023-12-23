package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public class King extends SpecialPiece {
    public King(PlayerColor color, Board board) {
        super(color, PieceType.KING, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Board board, boolean capture) {

        int xDiff = Math.abs(fromX - toX);
        int yDiff = Math.abs(fromY - toY);
        int xCorner = fromX - toX < 0 ? 7 : 0 ;
        // 1 square
        if (xDiff == 1 && yDiff == 0 || xDiff == 0 && yDiff == 1 || xDiff == 1 && yDiff == 1) {
            super.moved = true;
            return true;
        }

        // Castle
        Piece rook = board.getPieces()[xCorner][fromY];
        return (xDiff == 2 || (capture && xCorner == toX)) && yDiff == 0 && canCastle(rook, xCorner, fromX, fromY);
    }

    private boolean canCastle(Piece rook, int xCorner, int fromX, int fromY) {
        // Check nor the king nor the rook have moved before
        if (this.hasNotMoved() && rook instanceof Rook && ((Rook) rook).hasNotMoved()) {
            // Check there are no pieces in between the king and the rook
            for (int i = 1; i < Math.abs(fromX - xCorner); ++i) {
                if (board.getPieces()[xCorner + (xCorner == 0 ? i : -i)][fromY] != null) {
                    return false;
                }
            }
            // Check the king does not move over any spaces in which he would be checked
            return !board.kingInDanger(fromX, fromY, fromX - (xCorner == 0 ? 1 : -1), fromY, false);
        }
        return false;
    }

    public String textValue() {
        return "King";
    }
}
