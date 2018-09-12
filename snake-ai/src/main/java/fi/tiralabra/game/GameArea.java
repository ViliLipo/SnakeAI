/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

/**
 *
 * @author vili
 */
public class GameArea implements Cloneable {

    int[] table;
    private final int width = 25;
    private final int height = 25;

    public GameArea() {
        table = new int[(height) * (width)];
        this.makeBorders();
    }

    private void makeBorders() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height || j == 0 || j == width) {
                    table[(i * width) + j] = 3;
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
        return (this.table[location] == 1 || this.table[location] == 3);
    }

    public int getLocationValue(int x, int y) {
        return this.table[(width * y) + x];
    }

    /**
     *
     * @param x X coordinate of this location
     * @param y Y coordinate of this location
     * @return true if it is apple else false
     */
    public boolean checkApple(int x, int y) {
        int location = (width * y) + x;
        return (this.table[location] == 2);
    }

    @Override
    public GameArea clone() throws CloneNotSupportedException {
        GameArea a = (GameArea) GameArea.super.clone();
        for (int i = 0; i < this.table.length; i++) {
            a.table[i] = this.table[i];
        }
        return a;
    }

}
