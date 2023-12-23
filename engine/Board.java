package engine;

import chess.PlayerColor;
import engine.piece.*;

public class Board {

    public interface AddListener {
        void action(Piece piece, int x, int y);
    }

    public interface CaptureListener {
        void action(int x, int y);
    }

    public interface PromoteListener {
        void action(Piece piece, int x, int y);
    }

    public interface CastleListener {
        void action(int kingX, int rookX, int y);
    }

    private AddListener onAdd;
    private CaptureListener onCapture;
    private PromoteListener onPromotion;
    private CastleListener onCastle;

    private Piece[][] pieces;

    private Piece lastMoved;

    private PlayerColor playerTurn;
    public Board() {
        pieces = new Piece[8][8];
        lastMoved = null;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {this.pieces = pieces;}

    public Piece getLastMoved() {
        return lastMoved;
    }

    public PlayerColor getPlayerTurn() {return playerTurn;}

    public void setPlayerTurn(PlayerColor playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setAddListener(AddListener onAdd) {
        this.onAdd = onAdd;
    }

    public void setCaptureListener(CaptureListener onCapture) {
        this.onCapture = onCapture;
    }

    public void setPromotionListener(PromoteListener onPromotion) {
        this.onPromotion = onPromotion;
    }

    public void setCastleListener(CastleListener onCastle) {
        this.onCastle = onCastle;
    }

    public void setPiece(Piece piece, int x, int y) {
        pieces[x][y] = piece;

        if (onAdd != null)
            onAdd.action(piece, x, y);
    }

    public void removePiece(int x, int y) {
        pieces[x][y] = null;

        if (onCapture != null) {
            onCapture.action(x,y);
        }
    }

    public boolean canMove(int fromX, int fromY, int toX, int toY) {
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

        // Check not capturing comrades unless castle
//        if (capture && pieces[toX][toY].getColor() == piece.getColor()) {
        if (capture && pieces[toX][toY].getColor() == piece.getColor() && !(piece instanceof King)) {
            return false;
        }

        // Check for valid move
        if (piece.validMove(fromX,fromY,toX,toY,this, capture)) {

            // Check the move does not put the king in check
            return !kingInDanger(fromX, fromY, toX, toY, capture);
        }
        return false;
    }

    public void move(int fromX, int fromY, int toX, int toY) {

        Piece piece = pieces[fromX][fromY];

        boolean capture = pieces[toX][toY] != null;

        // Check if castle
        if (piece instanceof King && Math.abs(fromX - toX) > 1) {
            if (onCastle != null) {
                onCastle.action(fromX, fromX - toX > 0 ? 0 : 7, fromY);
                setPiece(piece,fromX - toX > 0 ? 2 : 6,fromY);
            }
        }
        else {
            setPiece(piece,toX,toY);
        }

        removePiece(fromX,fromY);

        // Pawn move
        if (piece instanceof Pawn p) {
            ((Pawn) piece).setLastMoveDist(Math.abs(fromY - toY));

            // Promotion
            if (toY == 7 || toY == 0) {
                promotePawn(p,toX,toY);
            }

            // If valid move and diagonal not capture -> en passant
            if (!capture && fromX != toX) {
                removePiece(toX,toY - (piece.getColor() == PlayerColor.WHITE ? 1 : -1));
            }
        }

        if (piece instanceof SpecialPiece) {
            ((SpecialPiece) piece).moved();
        }

        // Switch which colour is playing
        playerTurn = playerTurn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;

        lastMoved = piece;
    }

    /**
     * Promotes a pawn to a piece chosen by the user.
     *
     * @param pawn  The pawn to promote.
     * @param toX   The x coordinate of the pawn.
     * @param toY   The y coordinate of the pawn.
     */
    private void promotePawn(Pawn pawn, int toX, int toY) {
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
            // Put back the pieces
            pieces[toX][toY] = victim;
            pieces[fromX][fromY] = piece;
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

    public boolean isCheckMate() {
        for (int i = 0; i < pieces.length; ++i) {
            for (int j = 0; j < pieces.length; ++j) {
                if (pieces[i][j] != null && pieces[i][j].getColor() == playerTurn) {
                    for (int k = 0; k < pieces.length; ++k) {
                        for (int l = 0; l < pieces.length; ++l) {
                            if (canMove(i,j,k,l)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
