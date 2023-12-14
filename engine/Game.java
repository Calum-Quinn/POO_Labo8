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

        Piece piece = board[fromX][fromY];
        return piece.validMove(fromX,fromY,toX,toY,board, capture);
    }

    /**
     * Initialize the board with the pieces at their starting positions.
     */
    @Override
    public void newGame() {
        // Pawns
        for (int i = 0; i < SIZE; i++) {
            board[i][1] = new Pawn(PlayerColor.WHITE,board);
            board[i][6] = new Pawn(PlayerColor.BLACK,board);
            view.putPiece(PieceType.PAWN, PlayerColor.WHITE, i, 1);
            view.putPiece(PieceType.PAWN, PlayerColor.BLACK, i, 6);
        }

        // Rooks
        board[0][0] = new Rook(PlayerColor.WHITE,board);
        board[7][0] = new Rook(PlayerColor.WHITE,board);
        board[0][7] = new Rook(PlayerColor.BLACK,board);
        board[7][7] = new Rook(PlayerColor.BLACK,board);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 7, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 0, 7);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 7, 7);

        // Knights
        board[1][0] = new Knight(PlayerColor.WHITE,board);
        board[6][0] = new Knight(PlayerColor.WHITE,board);
        board[1][7] = new Knight(PlayerColor.BLACK,board);
        board[6][7] = new Knight(PlayerColor.BLACK,board);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 1, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 6, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 1, 7);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 6, 7);

        // Bishops
        board[2][0] = new Bishop(PlayerColor.WHITE,board);
        board[5][0] = new Bishop(PlayerColor.WHITE,board);
        board[2][7] = new Bishop(PlayerColor.BLACK,board);
        board[5][7] = new Bishop(PlayerColor.BLACK,board);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 2, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 5, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 2, 7);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 5, 7);

        // Queens
        board[3][0] = new Queen(PlayerColor.WHITE,board);
        board[3][7] = new Queen(PlayerColor.BLACK,board);
        view.putPiece(PieceType.QUEEN, PlayerColor.WHITE, 3, 0);
        view.putPiece(PieceType.QUEEN, PlayerColor.BLACK, 3, 7);

        // Kings
        board[4][0] = new King(PlayerColor.WHITE,board);
        board[4][7] = new King(PlayerColor.BLACK,board);
        view.putPiece(PieceType.KING, PlayerColor.WHITE, 4, 0);
        view.putPiece(PieceType.KING, PlayerColor.BLACK, 4, 7);
    }
}
