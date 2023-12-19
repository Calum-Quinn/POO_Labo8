package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class Pawn extends SpecialPiece {
    public Pawn(PlayerColor color, Piece[][] board) {
        super(color, PieceType.PAWN, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {

        boolean valid = false;

        // Factorisation between white and black pawns
        int whiteBlack = 1;
        if (color == PlayerColor.BLACK) {
            whiteBlack = -1;
        }

        // Straight line
        if (!capture && fromX == toX) {
            // 1 square
            if (fromY == toY - whiteBlack) {
//                return true;
                valid = true;
            }
            // 2 square, check that the pawn is on the correct row and no piece in front
            else {
                valid = fromY == toY - 2*whiteBlack && board[fromX][toY - whiteBlack] == null && (whiteBlack == 1 && toY == 3 || whiteBlack == - 1 && toY == 4);
            }
        }
        // Capture
        else if (capture && Math.abs(fromX - toX) == 1 && fromY == toY - whiteBlack) {
            valid = true;
        }
        // En passant
        else {
            // Check correct move format
            if (Math.abs(toX - fromX) == 1 && fromY == toY - whiteBlack) {
                Piece otherPawn = board[toX][fromY];
                // Check if nothing on destination square and neighboring piece is a pawn

                // IL FAUT CONTROLER QUE L'AUTRE PION A BOUGÉ LE COUP *PRÉCÉDENT*

                if (!capture && otherPawn instanceof Pawn && ((Pawn) otherPawn).hasMoved()) {
                    valid = true;
                    board[toX][fromY] = null;
                }
            }
        }
        if (valid) {
            super.moved = true;
        }
        return valid;
    }
    public String textValue() {
        return "Pawn";
    }
}