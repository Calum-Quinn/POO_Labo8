package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

public class Queen extends Piece {
    public Queen(PlayerColor color, Board board) {
        super(color, PieceType.QUEEN, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Board board, boolean capture) {

        // Check if valid bishop or rook move
        Bishop bishop = new Bishop(color,board);
        Rook rook = new Rook(color,board);
        return bishop.validMove(fromX,fromY,toX,toY,board,capture) || rook.validMove(fromX,fromY,toX,toY,board,capture);
    }

    public String textValue() {
        return "Queen";
    }
}
