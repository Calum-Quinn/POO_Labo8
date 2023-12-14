package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class King extends SpecialPiece {
    public King(PlayerColor color, Piece[][] board) {
        super(color, PieceType.KING, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {
        return false;
    }
}
