package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;

public class Game implements ChessController {
    private static final int SIZE = 8;
    private ChessView view;
    private Piece[][] board;

    public Game() {
        board = new Piece[8][8];
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        this.view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        // Check if there is a piece on the destination square
        boolean capture = board[toX][toY] != null;



        return false;
    }

    /**
     * Initialize the board with the pieces at their starting positions.
     */
    @Override
    public void newGame() {
        // Pawns
        for (int i = 0; i < SIZE; i++) {
            board[i][1] = new Pawn(PlayerColor.WHITE);
            board[i][6] = new Pawn(PlayerColor.BLACK);
            view.putPiece(PieceType.PAWN, PlayerColor.WHITE, i, 1);
            view.putPiece(PieceType.PAWN, PlayerColor.BLACK, i, 6);
        }

        // Rooks
        board[0][0] = new Rook(PlayerColor.WHITE);
        board[7][0] = new Rook(PlayerColor.WHITE);
        board[0][7] = new Rook(PlayerColor.BLACK);
        board[7][7] = new Rook(PlayerColor.BLACK);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 7, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 0, 7);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 7, 7);

        // Knights
        board[1][0] = new Knight(PlayerColor.WHITE);
        board[6][0] = new Knight(PlayerColor.WHITE);
        board[1][7] = new Knight(PlayerColor.BLACK);
        board[6][7] = new Knight(PlayerColor.BLACK);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 1, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 6, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 1, 7);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 6, 7);

        // Bishops
        board[2][0] = new Bishop(PlayerColor.WHITE);
        board[5][0] = new Bishop(PlayerColor.WHITE);
        board[2][7] = new Bishop(PlayerColor.BLACK);
        board[5][7] = new Bishop(PlayerColor.BLACK);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 2, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 5, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 2, 7);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 5, 7);

        // Queens
        board[3][0] = new Queen(PlayerColor.WHITE);
        board[3][7] = new Queen(PlayerColor.BLACK);
        view.putPiece(PieceType.QUEEN, PlayerColor.WHITE, 3, 0);
        view.putPiece(PieceType.QUEEN, PlayerColor.BLACK, 3, 7);

        // Kings
        board[4][0] = new King(PlayerColor.WHITE);
        board[4][7] = new King(PlayerColor.BLACK);
        view.putPiece(PieceType.KING, PlayerColor.WHITE, 4, 0);
        view.putPiece(PieceType.KING, PlayerColor.BLACK, 4, 7);
    }

    public boolean movePiece() {
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
