package engine;

import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;

import java.util.HashMap;

public class Board {
    private static final int SIZE = 8;
    private HashMap<Position, Piece> pieces;

    public Board() {
        pieces = new HashMap<>();
    }

    public void initialize() {
        for (PlayerColor color : PlayerColor.values()) {
            int line = color == PlayerColor.WHITE ? 0 : SIZE - 1;

            addPiece(new Rook(color, new Position(0, line)));
            addPiece(new Knight(color, new Position(1, line)));
            addPiece(new Bishop(color, new Position(2, line)));
            addPiece(new Queen(color, new Position(3, line)));
            addPiece(new Bishop(color, new Position(5, line)));
            addPiece(new Knight(color, new Position(6, line)));
            addPiece(new Rook(color, new Position(7, line)));

            int pawnLine = (color == PlayerColor.WHITE ? 1 : 6);
            for (int j = 0; j < SIZE; j++) {
                addPiece(new Pawn(color, new Position(j, pawnLine)));
            }
        }

    }

    public void addPiece(Piece piece) {
        pieces.put(piece.getPosition(), piece);
    }

    public boolean movePiece(Position from, Position to) {
        /*boolean valid = false;
        PieceType type = null;

        for (Piece piece : board.getPieces()) {
            if (piece.x == fromX && piece.y == fromY) {
                type = piece.type;
            }
        }
        // No piece there
        if (type == null) {
            return false;
        }

        *//*
        https://stackoverflow.com/questions/54972474/how-to-determine-valid-chess-moves



    Valid King move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if |X2-X1|<=1 and |Y2-Y1|<=1.

    Valid Bishop move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if |X2-X1|=|Y2-Y1|.

    Valid Rook move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if X2=X1 or Y2=Y1.

    Valid Queen move, a queen's move is valid if it is either a valid bishop or rook move.

    Valid Knight move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if (|X2-X1|=1 and |Y2-Y1|=2) or (|X2-X1|=2 and |Y2-Y1|=1).

    Valid Pawn move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if X2=X1 and Y2-Y1=1 (only for a white pawn).


         *//*

        // IL FAUDRA CONTROLER QU'IL N'Y A PAS D'AUTRES PIECES SUR LE CHEMIN
        // LE SENS EST IMPORTANT POUR LES PIONS (DESCENDENT OU MONTENT)

        switch (type) {
            case KING : return Math.abs(fromX-toX) <= 1 && Math.abs(fromY - toY) <= 1;
            case KNIGHT: return Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 2 || Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY) == 1;
            case ROOK: return fromX == toX || fromY == toY;
            case BISHOP: return Math.abs(fromX - toX) == Math.abs(fromY - toY);
            case QUEEN: return fromX == toX || fromY == toY || Math.abs(fromX - toX) == Math.abs(fromY - toY);
            case PAWN: break;
            default: return false;
        }

        return valid;*/
        return false;
    }
}
