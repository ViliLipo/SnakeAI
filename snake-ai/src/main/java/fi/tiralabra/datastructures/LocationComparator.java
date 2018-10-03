/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import fi.tiralabra.game.Location;
import java.util.Comparator;

/**
 *
 * @author vili
 */
public class LocationComparator implements Comparator {

    private Location goal;
    private int heapsize;

    public LocationComparator(Location goal) {
        this.goal = goal;
        this.heapsize = 0;
    }

    @Override
    public int compare(Object t, Object t1) {
        Location l = (Location) t;
        Location l1 = (Location) t1;
        return Integer.compare(this.goal.distance(l), this.goal.distance(l1));
    }

}
