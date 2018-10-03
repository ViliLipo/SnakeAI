/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import fi.tiralabra.game.Location;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author vili
 */
public class PathComparator implements Comparator {

    private LocationComparator loccomp;

    public PathComparator(Location goal) {
        this.loccomp = new LocationComparator(goal);
    }

    @Override
    public int compare(Object t, Object t1) {
        LinkedList<Location> l1 = (LinkedList) t;
        LinkedList<Location> l2 = (LinkedList) t1;
        return loccomp.compare(l1.getLast(), l2.getLast());
    }

}
