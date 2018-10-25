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
    private GameArea area;

    public Location(int x, int y, GameArea area) {
        this.x = x;
        this.y = y;
        this.area = area;
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
    private int straightDistance(Location loc) {
        return(Math.abs(this.x - loc.x) + Math.abs(this.y - loc.y));
    }
    
    public int distance(Location loc) {
        int straight = this.straightDistance(loc);
        int throughRoof = this.straightDistance(new Location(this.x, 1, this.area)) +
                loc.straightDistance(new Location(this.x, this.area.getHeight() -2, this.area));
        int throughFloor = this.straightDistance(new Location(this.x, this.area.getHeight() -2, this.area))
                + loc.straightDistance(new Location(this.x, 1, this.area));
        int throughLeft = this.straightDistance(new Location(1, this.y, this.area)) +
                loc.straightDistance(new Location(this.x, this.area.getWidth()-2, this.area));
        int throughRight = this.straightDistance(new Location(this.area.getWidth()-2, this.y, this.area)) +
                loc.straightDistance(new Location(1, this.y, this.area));
        int min = Math.min(straight, throughRoof);
        min = Math.min(min, throughFloor);
        min = Math.min(min, throughLeft);
        min = Math.min(min, throughRight);
        return min;
    }

}
