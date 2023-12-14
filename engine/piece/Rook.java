package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Rook extends SpecialPiece {
    public Rook(PlayerColor color, Piece[][] board) {
        super(color, PieceType.ROOK, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {
        return false;
    }
}
