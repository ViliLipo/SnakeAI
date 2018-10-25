/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

/**
 * This class is sort of a relic from the pre refractor GameArea.
 * Provides way to keep (x,y) together and calculate distances.
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
    /**
     * Get distance between this location and other location
     * @param loc the other location
     * @return integer value of the distance
     */
    public int distance(Location loc) {
        return (Math.abs(this.x - loc.x) + Math.abs(this.y - loc.y));
    }

}
