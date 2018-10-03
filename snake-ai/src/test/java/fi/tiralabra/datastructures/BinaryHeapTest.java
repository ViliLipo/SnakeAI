/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.datastructures;

import fi.tiralabra.game.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vili
 */
public class BinaryHeapTest {
    
    private BinaryHeap<Location> heap;
    
    public BinaryHeapTest() {
    }
    
    @Before
    public void setUp() {
        this.heap = new BinaryHeap(5, new LocationComparator(new Location(5,5)));
        this.heap.insert(new Location(8,8));
    }
    

    @Test
    public void testBuildHeap() {

        
    }

    @Test
    public void testInsert() {
        heap.insert(new Location(6,6));
    }


    @Test
    public void testExtractClosest() {
        heap.insert(new Location(5,5));
        heap.insert(new Location(4,5));
        Location loc = heap.extract();
        assertEquals(5, loc.getX());
        loc = heap.extract();
        assertEquals(4, loc.getX());
        assertEquals(5, loc.getY());
        loc = heap.extract();
        assertEquals(8, loc.getX());
        assertEquals(8, loc.getY());
    }
    
}
