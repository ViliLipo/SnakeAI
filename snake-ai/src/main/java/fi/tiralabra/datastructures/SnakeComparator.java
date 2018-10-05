/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;
import java.util.Comparator;

/**
 *
 * @author vili
 */
public class SnakeComparator implements Comparator {

    private LocationComparator loccomp;

    public SnakeComparator(Location start, Location goal) {
        loccomp = new LocationComparator(start, goal);
    }

    @Override
    public int compare(Object t, Object t1) {
        Snake snake1 = (Snake) t;
        Snake snake2 = (Snake) t1;
        return loccomp.compare(snake1.getHead(), snake2.getHead());
    }

}
