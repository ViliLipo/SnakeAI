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
        this.heap.insert(new Location(8,8));
    }
    

    @Test
    public void testBuildHeap() {
        Location[] arr = new Location[5];
        arr[0] = new Location(5,5);
        arr[1] = new Location(7,7);
        heap.buildHeap(arr);
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
    @Test
    public void testAllocateMore() {
        for(int i =0; i < 1000; i++) {
            heap.insert(new Location(i,i));
        }
        assertEquals(1001, heap.getSize());
    }
    
}
