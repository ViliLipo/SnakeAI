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
public class LocationHeapTest {
    
    private LocationHeap heap;
    
    public LocationHeapTest() {
    }
    
    @Before
    public void setUp() {
        heap = new LocationHeap(new Location(5,5));
        heap.insert(new Location(8,8));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buildHeap method, of class LocationHeap.
     */
    @Test
    public void testBuildHeap() {
        Location[] arr = new Location[3];
        arr[0] = new Location(6,6);
        arr[1] = new Location(10,10);
        arr[2] = new Location(15,15);
        heap.buildHeap(arr);
        assertEquals(6, heap.extractClosest().getX());
        assertEquals(10, heap.extractClosest().getX());
        assertEquals(15, heap.extractClosest().getX());
    }

    @Test
    public void testInsert() {
        heap.insert(new Location(6,6));
    }

    /**
     * Test of extractClosest method, of class LocationHeap.
     */
    @Test
    public void testExtractClosest() {
        heap.insert(new Location(4,5));
        Location loc = heap.extractClosest();
        assertEquals(4, loc.getX());
        assertEquals(5, loc.getY());
        loc = heap.extractClosest();
        assertEquals(8, loc.getX());
        assertEquals(8, loc.getY());
    }
    
}