/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.datastructures.LinkedList;

/**
 * Class contains functionality of the game area.
 * @author vili
 *
 */
public class GameArea implements Cloneable {

    int[][] table;
    private final int width = 50;
    private final int height = 50;
    public final static int FREE = 0;
    public final static int SNAKE = 1;
    public final static int APPLE = 2;
    public final static int WALL = 3;

    public GameArea() {
        table = new int[height][width];
        this.makeBorders();
    }

    /**
     * reset this area
     */
    public void reset() {
        table = new int[height][width];
        this.makeBorders();
    }

    private void makeBorders() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    table[i][j] = WALL;
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
        if (x >= this.width || y >= this.height) {
            return true;
        }
        return (this.table[y][x] == SNAKE || this.table[y][x] == WALL);
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
        return this.table[y][x];
    }

    /**
     * Checks if (x,y) is a corner. used to place apples so that those wont end
     * up in a corner
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return true if (x,y) is a corner else false
     */
    public boolean isCorner(int x, int y) {
        if (x == 1 && y == 1) {
            return true;
        } else if (x == this.width - 2 && y == 1) {
            return true;
        } else if (x == 1 && y == this.height - 2) {
            return true;
        } else if (x == this.width - 2 && y == this.getHeight() - 2) {
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
        if (x >= this.width || y >= this.height) {
            return false;
        }
        return (this.table[y][x] == APPLE);
    }

    @Override
    public GameArea clone() throws CloneNotSupportedException {
        GameArea a = (GameArea) GameArea.super.clone();
        a.table = new int[this.height][this.width];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                a.table[i][j] = this.table[i][j];
            }
        }
        return a;
    }

    /**
     * Place apple to area
     *
     * @param loc Location on which the apple will be placed
     */
    public void placeApple(Location loc) {
        this.table[loc.getY()][loc.getX()] = APPLE;
    }

    /**
     * Place a piece of snake
     *
     * @param loc Location on which the snake block will be placed
     */
    public void placeSnakePiece(Location loc) {
        this.table[loc.getY()][loc.getX()] = SNAKE;
    }

    /**
     * Set the specified location to be free.
     *
     * @param loc clear this location
     */
    public void clearLocation(Location loc) {
        this.table[loc.getY()][loc.getX()] = FREE;
    }

    public int[][] getTable() {
        return this.table;
    }

    /**
     * Get all free locations in a list
     *
     * @return List of locations
     */
    public LinkedList<Location> freeLocations() {
        LinkedList<Location> locations = new LinkedList<>();
        for (int j = 0; j < this.table.length; j++) {
            for (int i = 0; i < this.table[j].length; i++) {
                Location loc = new Location(i, j, this);
                if (!this.checkCollision(loc)) {
                    locations.add(loc);
                }
            }
        }
        return locations;
    }

}
