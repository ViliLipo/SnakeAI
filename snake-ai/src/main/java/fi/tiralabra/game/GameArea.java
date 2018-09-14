/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

/**
 *
 * @author vili
 * 
 * 
 * 
 * 
 */
public class GameArea implements Cloneable {

    int[] table;
    private final int width = 26;
    private final int height = 26;
    private final static int FREE = 0;
    private final static int SNAKE = 1;
    private final static int APPLE = 2;
    private final static int WALL = 3;

    public GameArea() {
        table = new int[(height) * (width)];
        this.makeBorders();
    }

    private void makeBorders() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height || j == 0 || j == width) {
                    table[(i * width) + j] = WALL;
                }
            }
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    /**
     *
     * @param x X coordinate of this location
     * @param y Y coordinate of this location
     * @return true if it is a wall or snake else false
     */
    public boolean checkCollision(int x, int y) {
        int location = (width * y) + x;
        return (this.table[location] == SNAKE || this.table[location] == WALL);
    }
    /**
     * 
     * @param loc Location to which check on the map
     * @return true if it is a wall or snake, else false
     */
    public boolean checkCollision(Location loc) {
        return this.checkCollision(loc.getX(), loc.getY());
    }

    /**
     * 
     * @param x X-coordinate of this location
     * @param y Y-coordinate of this location
     * @return the value integer value of that location
     */
    public int getLocationValue(int x, int y) {
        return this.table[(width * y) + x];
    }
    
    public boolean isCorner(int x, int y) {
        if (x == 1 && y == 1) {
            return true;
        } else if ( x == this.width -2 && y == 1) {
            return true;
        } else if ( x == 1 && y == this.height -2) {
            return true;
        } else if (x == this.width-2 && y == this.getHeight() -2) {
            return true;
        }
        
        return false;
    }

    /**
     *
     * @param x X coordinate of this location
     * @param y Y coordinate of this location
     * @return true if it is apple else false
     */
    public boolean checkApple(int x, int y) {
        int location = (width * y) + x;
        return (this.table[location] == APPLE);
    }

    @Override
    public GameArea clone() throws CloneNotSupportedException {
        GameArea a = (GameArea) GameArea.super.clone();
        for (int i = 0; i < this.table.length; i++) {
            a.table[i] = this.table[i];
        }
        return a;
    }
    
    public void placeApple(Location loc) {
        this.table[loc.toInt()] = APPLE;
    }
    
    public void placeSnakePiece(Location loc) {
        this.table[loc.toInt()] = SNAKE;
    }
    
    public void clearLocation(Location loc) {
        this.table[loc.toInt()] = 0;
    }

}
