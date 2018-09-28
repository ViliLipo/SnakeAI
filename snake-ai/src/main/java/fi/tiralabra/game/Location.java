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

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public Location getTop() {
        return new Location(this.x, this.y - 1);
    }

    public Location getRight() {
        return new Location(this.x + 1, this.y);
    }

    public Location getBottom() {
        return new Location(this.x, this.y + 1);
    }

    public Location getLeft() {
        return new Location(this.x - 1, this.y);
    }

    public boolean validate(GameArea area) {
        return (this.x > 0 && this.x < area.getWidth() && this.y > 0 && this.y < area.getWidth());
    }
    
    @Override
    public String toString() {
        return ("X:" + String.valueOf(this.x) + "Y:" + String.valueOf(this.y));
    }
    /**
     * Get distance between this location and other location
     * @param loc the other location
     * @return integer value of the distance
     */
    public int distance(Location loc) {
        return (Math.abs(this.x - loc.x) + Math.abs(this.y - loc.y));
    }

}
