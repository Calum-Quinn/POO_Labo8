package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;

public class Game implements ChessController {
    private static final int SIZE = 8;
    private ChessView view;
    private final Board board;

    public Game() {
        board = new Board();
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        this.view.startView();
        board.setPlayerTurn(PlayerColor.WHITE);

        // Event listeners
        board.setAddListener((piece, x, y) -> view.putPiece(piece.getType(), piece.getColor(), x, y));

        board.setCaptureListener(view::removePiece);

        board.setPromotionListener((piece, x, y) -> {
            PlayerColor color = piece.getColor();
            Piece[] choices = {
                    new Queen(color,board),
                    new Knight(color,board),
                    new Rook(color,board),
                    new Bishop(color,board)
            };

            Piece userChoice;
            do {
                userChoice = view.askUser("Promotion", "Choose a piece to promote to", choices);
            } while (userChoice == null);


            board.removePiece(x,y);
            board.setPiece(userChoice, x, y);
        });

        board.setCastleListener(((kingX,rookX,y) -> {
            King king = (King) board.getPieces()[kingX][y];
            Rook rook = (Rook) board.getPieces()[rookX][y];
            int kingTo = kingX - rookX > 0 ? 2 : 6;
            int rookTo = kingTo == 2 ? 3 : 5;
            board.removePiece(rookX,y);
            board.removePiece(kingX,y);
            board.setPiece(king,kingTo,y);
            board.setPiece(rook,rookTo,y);
        }));
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return board.move(fromX, fromY, toX, toY);
    }

    /**
     * Initialize the board with the pieces at their starting positions.
     */
    @Override
    public void newGame() {
        // Pawns
        for (int i = 0; i < SIZE; i++) {
            board.getPieces()[i][1] = new Pawn(PlayerColor.WHITE,board);
            board.getPieces()[i][6] = new Pawn(PlayerColor.BLACK,board);
            view.putPiece(PieceType.PAWN, PlayerColor.WHITE, i, 1);
            view.putPiece(PieceType.PAWN, PlayerColor.BLACK, i, 6);
        }

        // Rooks
        board.getPieces()[0][0] = new Rook(PlayerColor.WHITE,board);
        board.getPieces()[7][0] = new Rook(PlayerColor.WHITE,board);
        board.getPieces()[0][7] = new Rook(PlayerColor.BLACK,board);
        board.getPieces()[7][7] = new Rook(PlayerColor.BLACK,board);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 7, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 0, 7);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 7, 7);

        // Knights
        board.getPieces()[1][0] = new Knight(PlayerColor.WHITE,board);
        board.getPieces()[6][0] = new Knight(PlayerColor.WHITE,board);
        board.getPieces()[1][7] = new Knight(PlayerColor.BLACK,board);
        board.getPieces()[6][7] = new Knight(PlayerColor.BLACK,board);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 1, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 6, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 1, 7);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 6, 7);

        // Bishops
        board.getPieces()[2][0] = new Bishop(PlayerColor.WHITE,board);
        board.getPieces()[5][0] = new Bishop(PlayerColor.WHITE,board);
        board.getPieces()[2][7] = new Bishop(PlayerColor.BLACK,board);
        board.getPieces()[5][7] = new Bishop(PlayerColor.BLACK,board);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 2, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 5, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 2, 7);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 5, 7);

        // Queens
        board.getPieces()[3][0] = new Queen(PlayerColor.WHITE,board);
        board.getPieces()[3][7] = new Queen(PlayerColor.BLACK,board);
        view.putPiece(PieceType.QUEEN, PlayerColor.WHITE, 3, 0);
        view.putPiece(PieceType.QUEEN, PlayerColor.BLACK, 3, 7);

        // Kings
        board.getPieces()[4][0] = new King(PlayerColor.WHITE,board);
        board.getPieces()[4][7] = new King(PlayerColor.BLACK,board);
        view.putPiece(PieceType.KING, PlayerColor.WHITE, 4, 0);
        view.putPiece(PieceType.KING, PlayerColor.BLACK, 4, 7);

        board.setPlayerTurn(PlayerColor.WHITE);
    }
}
