/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.datastructures.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author vili
 */
public class Apple {

    private Location location;
    private GameArea area;

    /**
     * Create Apple
     *
     * @param area GameArea
     */
    public Apple(GameArea area){
        this.area = area;
    }
    /**
     * Place apple in to the gamearea
     * @throws Exception if there is no space for apple in the gamearea.
     */
    public void placeApple() throws Exception {
        LinkedList<Location> locations = this.area.freeLocations();
        if(locations.isEmpty()) {
            throw new Exception("No free space for apple");
        }
        int index = ThreadLocalRandom.current().nextInt(1, locations.size());
        this.area.placeApple(locations.get(index));
        this.location = locations.get(index);
    }

    public Location getLocation() {
        return this.location;
    }
}
