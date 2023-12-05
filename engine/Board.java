package engine;

import chess.PlayerColor;
import engine.piece.*;

import java.util.ArrayList;
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
}
