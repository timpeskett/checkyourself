package com.timandjake.checkyourself.model;

import com.badlogic.graphics.Texture;
import java.util.HashMap;


public class Piece {
    public enum Type {
        WHITE, BLACK, WHITEKING, BLACKKING
    }

    private static HashMap<Type, String> imgNames;

    private Type type;
    private BoardCoord pos;


    public Piece(Type t, BoardCoord initial) {
        this.type = t;
        setPos(initial);
    }


    public boolean isWhite() {
        return type == WHITE || type == WHITEKING;
    }

    public boolean isBlack() {
        return !isWhite();
    }

    public boolean isKing() {
        return type == WHITEKING || type == BLACKKING;
    }

    /* Returns true if this piece and the passed in piece are enemies. */
    public boolean isEnemy(Piece p) {
        return isBlack() == p.isWhite();
    }

    public BoardCoord getPos() {
        return pos;
    }

    /* Two pieces are considered equal if they are at the same position. */
    public boolean equals(Object o) {
        Piece p;
        if(o instanceof Piece) {
            p = (Piece)o;
            return p.getPos().equals(getPos());
        }

        return false;
    }

    /* Can't be called from outside this package! */
    protected void setPos(BoardCoord bc) {
        pos = new BoardCoord(bc);
    }


    /* Class initializer */
    static {
        imgNames = new HashMap<Type, String>();

        imgNames.put(WHITE, "white_piece.img");
        imgNames.put(BLACK, "black_piece.img");
        imgNames.put(WHITEKING, "white_king_piece.img");
        imgNames.put(BLACKKING, "black_king_piece.img");
    }
}

