package engine;

import chess.ChessController;
import chess.ChessView;

public class Game implements ChessController {

    private Board board;

    Game() {
        board = new Board(this);
    }

    @Override
    public void start(ChessView view) {

    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public void newGame() {

    }
}
