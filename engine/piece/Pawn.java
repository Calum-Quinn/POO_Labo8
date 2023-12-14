package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece {
    public Pawn(PlayerColor color, Piece[][] board) {
        super(color, PieceType.PAWN, board);
    }

    public void promote(PieceType type) {
        this.type = type;
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {

        // Factorisation between white and black pawns
        int whiteBlack = 1;
        if (color == PlayerColor.BLACK) {
            whiteBlack = -1;
        }

        // Straight line
        if (!capture && fromX == toX) {
            // 1 square
            if (fromY == toY - whiteBlack) {
                return true;
            }
            // 2 square, check that the pawn is on the correct row and no piece in front
            else return fromY == toY - 2*whiteBlack && board[fromX][toY - whiteBlack] == null && (whiteBlack == 1 && toY == 3 || whiteBlack == - 1 && toY == 4);
        }
        // Capture
        else if (capture && Math.abs(fromX - toX) == 1 && fromY == toY - whiteBlack) {
            return true;
        }
        // En passant
        else {
            // Check correct move format
            if (!(Math.abs(toX - fromX) == 1 && fromY == toY - whiteBlack)) {
                Piece otherPawn = board[toX][fromY];
                // Check if nothing on destination square and neighboring piece is a pawn
                return !capture && otherPawn instanceof Pawn && ((Pawn) otherPawn).hasMoved();
            }
            return false;
        }
    }
}