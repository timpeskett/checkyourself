package com.timandjake.checkyourself.model;


public class BoardCoord {
    private int boardSize;
    /* x, y range between 1 and boardSize */
    private int x, y;

    public BoardCoord(int boardSize, int x, int y) {
        if(boardSize < 1) {
            throw new IllegalArgumentException("Board size is too small!");
        }
        this.boardSize = boardSize;

        setX(x);
        setY(y);
    }

    public BoardCoord(BoardCoord bc) {
        this.boardSize = bc.boardSize;

        setX(bc.getX());
        setY(bc.getY());
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setX(int x) {
        if(x < 1 || x > boardSize) {
            throw new IllegalArgumentException("X must be in [1, " + boardSize + "]");
        }
        this.x = x;
    }

    public void setY(int y) {
        if(y < 1 || y > boardSize) {
            throw new IllegalArgumentException("Y must be in [1, " + boardSize+ "]");
        }
        this.y = y;
    }

    /* Takes this as the start board coordinate. Takes a middle board coordinate
     * as argument. Returns the board coordinate that is next on the diagonal
     * line between those two coordinates. Essentially it is the square that
     * a jumping piece would jump to.
     * Returns null if this piece is not on the board. */
    public BoardCoord getJumpCoord(BoardCoord bc) {
        int xDiff, yDiff;

        xDiff = bc.getX() - x;
        yDiff = bc.getY() - y;
        
        try {
            return new BoardCoord(boardSize, bc.getX() + xDiff, bc.getY() + yDiff);

        } catch(IllegalArgumentException e) {}

        return null;
    }
                           
                          

    public int hashCode() {
        return x * boardSize + y;
    }

    public boolean equals(Object o) {
        BoardCoord bc;

        if(o instanceof BoardCoord) {
            bc = (BoardCoord)o;
            if(bc.x == x && bc.y == y) {
                return true;
            }
        }

        return false;
    }
}


class BoardSizeException extends Exception {
    public BoardSizeException(String message) {
        super(message);
    }
}
