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
public class Location {

    private int x;
    private int y;
    private GameArea area;

    public Location(int x, int y, GameArea area) {
        this.x = x;
        this.y = y;
        this.area = area;
    }

    public Location(int loc) {
        this.y = loc / area.getWidth();
        this.x = loc - this.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int toInt() {
        return (this.y * this.area.getWidth() + this.x);
    }

    public Location getTop() {
        return new Location(this.x, this.y - 1, this.area);
    }

    public Location getRight() {
        return new Location(this.x + 1, this.y, this.area);
    }

    public Location getBottom() {
        return new Location(this.x, this.y + 1, this.area);
    }

    public Location getLeft() {
        return new Location(this.x - 1, this.y, this.area);
    }

    public boolean validate() {
        return (this.x > 0 && this.x < this.area.getWidth() && this.y > 0 && this.y < this.area.getWidth());
    }

}
