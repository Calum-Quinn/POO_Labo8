package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;

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

        boolean valid = false;
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

        /*
        https://stackoverflow.com/questions/54972474/how-to-determine-valid-chess-moves



    Valid King move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if |X2-X1|<=1 and |Y2-Y1|<=1.

    Valid Bishop move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if |X2-X1|=|Y2-Y1|.

    Valid Rook move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if X2=X1 or Y2=Y1.

    Valid Queen move, a queen's move is valid if it is either a valid bishop or rook move.

    Valid Knight move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if (|X2-X1|=1 and |Y2-Y1|=2) or (|X2-X1|=2 and |Y2-Y1|=1).

    Valid Pawn move, if the piece moves from (X1, Y1) to (X2, Y2), the move is valid if and only if X2=X1 and Y2-Y1=1 (only for a white pawn).


         */

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

        return valid;
    }

    @Override
    public void newGame() {

    }
}
