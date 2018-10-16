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
    private Location start;

    public LocationComparator(Location start, Location goal) {
        this.goal = goal;
        this.start = start;
    }

    @Override
    public int compare(Object t, Object t1) {
        Location l = (Location) t;
        Location l1 = (Location) t1;
        int value1 = this.start.distance(l) + this.goal.distance(l);
        int value2 = this.start.distance(l1) + this.goal.distance(l1);
        return Integer.compare(value1, value2);
    }

}
