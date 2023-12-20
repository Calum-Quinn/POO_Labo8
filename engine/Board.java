package engine;

import chess.PlayerColor;
import engine.piece.*;

public class Board {

    public interface PieceListener {
        void action(Piece piece, int x, int y);
    }

    private PieceListener onAdd;
    private PieceListener onCapture;

    private PieceListener onPromotion;

    private final Piece[][] pieces;

    private PlayerColor playerTurn;
    public Board() {
        pieces = new Piece[8][8];
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPlayerTurn(PlayerColor playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setAddListener(PieceListener onAdd) {
        this.onAdd = onAdd;
    }

    public void setCaptureListener(PieceListener onCapture) {
        this.onCapture = onCapture;
    }

    public void setPromotionListener(PieceListener onPromotion) {
        this.onPromotion = onPromotion;
    }

    public void setPiece(Piece piece, int x, int y) {
        pieces[x][y] = piece;

        if (onAdd != null)
            onAdd.action(piece, x, y);
    }

    public void removePiece(int x, int y) {
        if (onCapture != null) {
            onCapture.action(pieces[x][y],x,y);
        }
    }

    public boolean move(int fromX, int fromY, int toX, int toY) {

        Piece piece = pieces[fromX][fromY];

        // Check piece not moving
        if (piece == null || fromX == toX && fromY == toY) {
            return false;
        }

        // Check correct colour is playing
        if (playerTurn != piece.getColor()) {
            return false;
        }

        // Check if there is a piece on the destination square
        boolean capture = pieces[toX][toY] != null;

        // Check not capturing comrades
        if (capture && pieces[toX][toY].getColor() == piece.getColor()) {
            return false;
        }

        // Check the move does not put the king in check
        if (kingInDanger(fromX,fromY,toX,toY,capture)) {
            return false;
        }

        // Check for valid move
        if (piece.validMove(fromX,fromY,toX,toY,this, capture)) {

            // Move piece
            setPiece(piece,toX,toY);
            removePiece(fromX,fromY);

            // Pawn promotion
            if (piece instanceof Pawn p && (toY == 7 || toY == 0)) {
                pawnPromotion(p,toX,toY);
            }

            // Switch which colour is playing
            playerTurn = playerTurn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;

            return true;
        }
        return false;
    }

    private void pawnPromotion(Pawn pawn, int toX, int toY) {
        if (onPromotion != null) {
            onPromotion.action(pawn,toX,toY);
        }
    }

    public  int[] findKing(PlayerColor color) {
        int[] position = {-1,-1};
        for (int i = 0; i < pieces.length; ++i) {
            for (int j = 0; j < pieces.length; ++j) {
                if (pieces[i][j] != null && pieces[i][j] instanceof King && pieces[i][j].getColor() == color) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }

    public boolean kingInDanger(int fromX, int fromY, int toX, int toY, boolean capture) {
        // To check whether the king is in danger, we simulate the move being made
        Piece piece = pieces[fromX][fromY];
        Piece victim = null;
        // Check piece not moving to same square
        if (!(fromX == toX && fromY == toY)) {
            if (capture) {
                victim = pieces[toX][toY];
            }
            pieces[toX][toY] = piece;
            pieces[fromX][fromY] = null;
        }

        // Find the king
        int[] kingPos = findKing(piece.getColor());
        if (kingPos[0] != -1 && kingPos[1] != -1) {
            // Check that the King is not in check
            boolean check = isInCheck(piece.getColor());
            if (check) {
                // Put back the pieces
                pieces[toX][toY] = victim;
                pieces[fromX][fromY] = piece;
            }
            return check;
        }
        return false;
    }

    public boolean isInCheck(PlayerColor color) {
        int[] kingPos = findKing(color);
        for (int i = 0; i < pieces.length; ++i) {
            for (int j = 0; j < pieces.length; ++j) {
                if (pieces[i][j] != null && pieces[i][j].getColor() != color && pieces[i][j].validMove(i,j,kingPos[0],kingPos[1],this,true)) {
                    return true;
                }
            }
        }
        return false;
    }
}
