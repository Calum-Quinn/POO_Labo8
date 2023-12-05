package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import engine.piece.Piece;

public class Game implements ChessController {
    private Board board;

    Game() {
        board = new Board();
    }

    @Override
    public void start(ChessView view) {

    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Position from = new Position(fromX, fromY);
        Position to = new Position(toX, toY);

        return board.movePiece(from, to);
    }

    @Override
    public void newGame() {
        board.initialize();
    }
}
