/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.*;
import wormgame.Direction;

public class Worm {

    private int x;
    private int y;
    private Direction wormDirection;
    private ArrayList<Piece> pieces;
    private int growthCount = 0;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        x = originalX;
        y = originalY;
        wormDirection = originalDirection;
        pieces = new ArrayList<>();
        pieces.add(new Piece(x, y));
    }

    public Direction getDirection() {
        return wormDirection;
    }

    public void setDirection(Direction dir) {
        wormDirection = dir;
    }

    public int getLength() {
        return this.getPieces().size();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void move() {

        if (wormDirection == Direction.UP) {
            y--;

        } else if (wormDirection == Direction.DOWN) {
            y++;


        } else if (wormDirection == Direction.LEFT) {
            x--;


        } else if (wormDirection == Direction.RIGHT) {
            x++;
            
        }
        pieces.add(new Piece(x, y));

        if (this.getLength() <= 3 || growthCount >= 1) {
            if (growthCount >= 1) {
                growthCount--;
            }
        } else {
            pieces.remove(0);
        }

    }

    public void grow() {
        growthCount++;

    }

    public boolean runsInto(Piece piece) {
        for (Piece i : pieces) {
            if (i.getX() == piece.getX() && i.getY() == piece.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean runsIntoItself() {
        Piece firstPiece = pieces.get(pieces.size() - 1);

        for (int i = 0; i < this.getLength() - 1; i++) {

            if (firstPiece.runsInto(pieces.get(i))) {
                return true;
            }
        }

        return false;
    }
}
