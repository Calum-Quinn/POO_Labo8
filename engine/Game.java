package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.piece.*;

public class Game implements ChessController {
    private static final int BOARD_SIZE = 8;
    private ChessView view;
    private final Piece[][] board;

    private PlayerColor playerToPlay;

    public Game() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        playerToPlay = PlayerColor.WHITE;
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        this.view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Piece piece = board[fromX][fromY];

        // Check piece not moving
        if (piece == null || fromX == toX && fromY == toY) {
            return false;
        }

        // Check if correct colour is playing
        if (piece.getColor() != playerToPlay) {
            return false;
        }

        // Check if there is a piece on the destination square
        boolean capture = board[toX][toY] != null;

        // Check not capturing comrades
        if (capture && board[toX][toY].getColor() == piece.getColor()) {
            return false;
        }

        // Check the move does not put the king in check
        if (kingInDanger(fromX, fromY, toX, toY, capture)) {
            return false;
        }

        // Check for valid move
        if (piece.validMove(fromX, fromY, toX, toY, board, capture)) {
            // Move piece
            board[toX][toY] = piece;
            view.putPiece(piece.getType(), piece.getColor(), toX, toY);
            board[fromX][fromY] = null;
            view.removePiece(fromX, fromY);

            // Pawn promotion
            if (piece instanceof Pawn p && (toY == 7 || toY == 0)) {
                promotePawn(p, toX, toY);
            }

            // Switch which colour is playing
            nextPlayer();

            return true;
        }

        return false;
    }

    /**
     * Initialize the board with the pieces at their starting positions.
     */
    @Override
    public void newGame() {
        // Pawns
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][1] = new Pawn(PlayerColor.WHITE, board);
            board[i][6] = new Pawn(PlayerColor.BLACK, board);
            view.putPiece(PieceType.PAWN, PlayerColor.WHITE, i, 1);
            view.putPiece(PieceType.PAWN, PlayerColor.BLACK, i, 6);
        }

        // Rooks
        board[0][0] = new Rook(PlayerColor.WHITE, board);
        board[7][0] = new Rook(PlayerColor.WHITE, board);
        board[0][7] = new Rook(PlayerColor.BLACK, board);
        board[7][7] = new Rook(PlayerColor.BLACK, board);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.WHITE, 7, 0);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 0, 7);
        view.putPiece(PieceType.ROOK, PlayerColor.BLACK, 7, 7);

        // Knights
        board[1][0] = new Knight(PlayerColor.WHITE, board);
        board[6][0] = new Knight(PlayerColor.WHITE, board);
        board[1][7] = new Knight(PlayerColor.BLACK, board);
        board[6][7] = new Knight(PlayerColor.BLACK, board);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 1, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.WHITE, 6, 0);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 1, 7);
        view.putPiece(PieceType.KNIGHT, PlayerColor.BLACK, 6, 7);

        // Bishops
        board[2][0] = new Bishop(PlayerColor.WHITE, board);
        board[5][0] = new Bishop(PlayerColor.WHITE, board);
        board[2][7] = new Bishop(PlayerColor.BLACK, board);
        board[5][7] = new Bishop(PlayerColor.BLACK, board);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 2, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.WHITE, 5, 0);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 2, 7);
        view.putPiece(PieceType.BISHOP, PlayerColor.BLACK, 5, 7);

        // Queens
        board[3][0] = new Queen(PlayerColor.WHITE, board);
        board[3][7] = new Queen(PlayerColor.BLACK, board);
        view.putPiece(PieceType.QUEEN, PlayerColor.WHITE, 3, 0);
        view.putPiece(PieceType.QUEEN, PlayerColor.BLACK, 3, 7);

        // Kings
        board[4][0] = new King(PlayerColor.WHITE, board);
        board[4][7] = new King(PlayerColor.BLACK, board);
        view.putPiece(PieceType.KING, PlayerColor.WHITE, 4, 0);
        view.putPiece(PieceType.KING, PlayerColor.BLACK, 4, 7);
    }

    /**
     * Promotes a pawn to a piece chosen by the user.
     *
     * @param pawn The pawn to promote.
     * @param toX  The x coordinate of the pawn.
     * @param toY  The y coordinate of the pawn.
     */
    private void promotePawn(Pawn pawn, int toX, int toY) {
        // Check for pawn promotion
        PlayerColor color = pawn.getColor();
        Piece[] choices = {
                new Queen(color, board),
                new Knight(color, board),
                new Rook(color, board),
                new Bishop(color, board)
        };

        Piece userChoice;
        do {
            userChoice = view.askUser("Promotion", "Choisir une pièce pour la promotion", choices);
        } while (userChoice == null);

        board[toX][toY] = userChoice;
        view.putPiece(userChoice.getType(), color, toX, toY);
    }

    private boolean kingInDanger(int fromX, int fromY, int toX, int toY, boolean capture) {
        // To check whether the king is in danger, we simulate the move being made
        Piece piece = board[fromX][fromY];
        Piece victim = null;
        if (capture) {
            victim = board[toX][toY];
        }
        board[toX][toY] = piece;
        board[fromX][fromY] = null;

        // Find the king
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] != null && board[i][j] instanceof King && board[i][j].getColor() == piece.getColor()) {
                    // Check that the King is not in check
                    for (int k = 0; k < BOARD_SIZE; ++k) {
                        for (int l = 0; l < BOARD_SIZE; ++l) {
                            if (board[k][l] != null && board[k][l].getColor() != piece.getColor() && board[k][l].validMove(k, l, i, j, board, capture)) {
                                // Put back the pieces
                                board[toX][toY] = victim;
                                board[fromX][fromY] = piece;
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Switches which colour is playing.
     */
    private void nextPlayer() {
        playerToPlay = playerToPlay == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }
}
