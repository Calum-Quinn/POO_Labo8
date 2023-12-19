package engine.piece;

import chess.PieceType;
import chess.PlayerColor;

public class King extends SpecialPiece {
    public King(PlayerColor color, Piece[][] board) {
        super(color, PieceType.KING, board);
    }

    @Override
    public boolean validMove(int fromX, int fromY, int toX, int toY, Piece[][] board, boolean capture) {


        // IL FAUT CONTRÔLER QUE LE ROI NE SE DÉPLACE PAS SUR UNE CASE OU IL PEUT ÊTRE PRIT AU TOUR D'APRÈS
        //      DEPUIS LA CASE OÙ IL VEUT ALLER IL FAUT REGARDER TOUS LES DEPLACEMENTS POSSIBLES ET REGARDER SI UNE PIECE S'Y TROUVE QUI PEUT EFFECTER LE MOVE

        int xDiff = Math.abs(fromX - toX);
        int yDiff = Math.abs(fromY - toY);
        int xCorner = fromX - toX < 0 ? 7 : 0 ;
        // 1 square
        if (xDiff == 1 && yDiff == 0 || xDiff == 0 && yDiff == 1 || xDiff == 1 && yDiff == 1) {
            super.moved = true;
            return true;
        }

        // Castle
        Piece rook = board[xCorner][color == PlayerColor.WHITE ? 0 : 7];
        if (!this.hasMoved() && xDiff == 2 && rook instanceof Rook && !((Rook) rook).hasMoved()) {
            super.moved = true;
            return true;
        }
        return false;
    }

    public String textValue() {
        return "King";
    }
}
