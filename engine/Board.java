package engine;

import java.util.ArrayList;

public class Board {
    private Game game;
    private ArrayList<Piece> pieces;

    private int[] rows;

    private Columns columns;

    Board(Game game) {
        this.game = game;
        // Add pieces for each player, specify color and position
    }
}
