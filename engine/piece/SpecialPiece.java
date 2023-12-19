package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public abstract class SpecialPiece extends Piece{

    protected boolean moved;

    public SpecialPiece(PlayerColor color, PieceType type, Board board) {
        super(color, type, board);
        moved = false;
    }

    public boolean hasMoved() {
        return moved;
    }
}
