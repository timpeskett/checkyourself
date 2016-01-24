package com.timandjake.checkyourself.model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.lang.Iterable;


public class Board implements Iterable<Piece> {
    public enum PieceDirection {
        /* BoardCoord 1,1 is the bottom left of the board, and so DOWN will have
         * the y coordinate *decreasing* and UP will have the y coordinate
         * *increasing* */
        UP(-1), DOWN(1);

        private int val; 
        
        PieceDirection(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public PieceDirection opposite() {
            switch(this) {
                case UP:
                    return PieceDirection.DOWN;
                case DOWN:
                    return PieceDirection.UP;
                default:
                    throw new IllegalStateException("Only UP and DOWN possible");
            }
        }
            
    }


    private HashMap<BoardCoord, Piece> boardMap;

    private int boardSize;
    private PieceDirection whiteDirection;

    /* Board constructor. Creates board with pieces.
     * Params:
     *      boardSize - The size of the square board in squares. Must be at
     *      least 4 and must be even.
     *      whiteDirection - The direction that the white pieces initially move.
     *      If this is 'DOWN' then the white pieces will start at the top. If
     *      this is 'UP' then the white pieces will start at the bottom.
     */
    public Board(int boardSize, PieceDirection whiteDirection) {
        if(boardSize < 4) {
            throw new IllegalArgumentException("Board size is too small");
        }
        if(boardSize % 2 == 1) {
            throw new IllegalArgumentException("Board size must be even");
        }
        this.boardSize = boardSize;

        this.whiteDirection = whiteDirection;

        boardMap = new HashMap<BoardCoord, Piece>();

        createPieces(Piece.Type.BLACK, 1, boardSize / 2 - 1);
        createPieces(Piece.Type.WHITE, boardSize / 2 + 1, boardSize);
    }


    /* Used to create black and white pieces separately */
    private void createPieces(Piece.Type type, int startRow, int endRow) {
        /* Create white pieces */
        for(int i = startRow; i < endRow; i++) {
            for(int j = i % 2 + 1; j < boardSize; j += 2) {
                BoardCoord curr = new BoardCoord(boardSize, j, i);
                boardMap.put(curr, new Piece(type, curr));
            }
        }
    }

    public Iterator<Piece> iterator() {
        return boardMap.values().iterator();
    }

    /* Returns null if no piece at bc */
    public Piece getPiece(BoardCoord bc) {
        return boardMap.get(bc);
    }


    public boolean isEmpty(BoardCoord bc) {
        return boardMap.containsKey(bc);
    }


    public void movePiece(Piece p, BoardCoord dest) throws InvalidMoveException {
        Collection<BoardCoord> validMoves;

        validMoves = getValidMoves(p);
        if(validMoves.contains(dest)) {
            boardMap.remove(p.getPos());
            p.setPos(dest);
            boardMap.put(p.getPos(), p);
        }
        else {
            throw new InvalidMoveException("Invalid move requested");
        }
    }


    /* Returns all valid moves for a piece, taking into account *ALL* the
     * rules of checkers. For example, if another piece can take, then this
     * piece will have no valid moves */
    public Collection<BoardCoord> getValidMoves(Piece p) {
        ArrayList<BoardCoord> validMoves = new ArrayList<BoardCoord>();
        Collection<Piece> attackingPieces;

        attackingPieces = getAttackingPieces();

        if(attackingPieces.isEmpty()) {
            validMoves.addAll(getAdjacentSquares(p));
        }
        else if(attackingPieces.contains(p)) {
            for(BoardCoord bc : getPieceAttacks(p)) {
                validMoves.add(p.getPos().getJumpCoord(bc));
            }
        }

        return validMoves;
    }


    /* Returns a collection of all pieces that can take another piece.
     * In checkers, a piece must take if able. */
    public Collection<Piece> getAttackingPieces() {
        ArrayList<Piece> pieces = new ArrayList<Piece>();

        for(Piece p : this) {
            if(!getPieceAttacks(p).isEmpty()){
                pieces.add(p);
            }
        }
        
        return pieces;
    }


    public Collection<BoardCoord> getPieceAttacks(Piece p) {
        ArrayList<BoardCoord> attacks = new ArrayList<BoardCoord>();

        for(BoardCoord adj : getAdjacentSquares(p)) {
            if(!isEmpty(adj) && p.getPos().getJumpCoord(adj) != null) {
                attacks.add(adj);
            }
        }

        return attacks;
    }


    /* Returns the squares that this piece could move to, if all squares
     * around it were empty. For a kinged piece, this will be four squares,
     * for a black or white piece this will be two squares. */
    private Collection<BoardCoord> getAdjacentSquares(Piece p) {
        if(p.isWhite() && !p.isKing())
        {
            return getAdjacentSquaresDir(p.getPos(), whiteDirection);
        }
        if(p.isBlack() && !p.isKing())
        {
            return getAdjacentSquaresDir(p.getPos(), whiteDirection.opposite());
        }
        if(p.isKing())
        {
            Collection<BoardCoord> moves;

            moves = getAdjacentSquaresDir(p.getPos(), whiteDirection);
            moves.addAll(getAdjacentSquaresDir(p.getPos(), whiteDirection.opposite()));
            return moves;
        }

        return null;
    }


    private Collection<BoardCoord> getAdjacentSquaresDir(BoardCoord bc, PieceDirection dir) {
        ArrayList<BoardCoord> squares = new ArrayList<BoardCoord>();
        BoardCoord temp;
        int dirVal = dir.getVal();

        /* Check for left of piece */
        try {
            temp = new BoardCoord(boardSize, bc.getX() - 1, bc.getY() + dirVal);
            squares.add(temp);
        } catch(IllegalArgumentException e) {}

        /* Check for right of piece */
        try {
            temp = new BoardCoord(boardSize, bc.getX() + 1, bc.getY() + dirVal);
            squares.add(temp);
        } catch(IllegalArgumentException e) {}

        return squares;
    }
}

