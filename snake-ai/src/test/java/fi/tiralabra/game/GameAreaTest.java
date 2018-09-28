/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vili
 */
public class GameAreaTest {

    private GameArea area;

    public GameAreaTest() {
    }

    @Before
    public void setUp() {
        this.area = new GameArea();
    }

    @After
    public void tearDown() {
        this.area.reset();
    }

    /**
     * Test of getHeight method, of class GameArea.
     */
    @Test
    public void testGetHeight() {
        int[][] table = area.getTable();
        assertEquals(table.length, area.getHeight());
    }

    /**
     * Test of getWidth method, of class GameArea.
     */
    @Test
    public void testGetWidth() {
        int[][] table = area.getTable();
        assertEquals(table[0].length, area.getWidth());
    }

    /**
     * Test of checkCollision method, of class GameArea.
     */
    @Test
    public void testCheckCollision_int_int() {
        assertTrue(area.checkCollision(0, 0));
        assertFalse(area.checkCollision(1, 1));
        int[][] table = area.getTable();
        table[1][1] = 1;
        assertTrue(area.checkCollision(1, 1));
    }

    /**
     * Test of checkCollision method, of class GameArea.
     */
    @Test
    public void testCheckCollision_Location() {
        Location loc = new Location(0, 0);
        assertTrue(area.checkCollision(loc));
        loc = new Location(1, 1);
        assertFalse(area.checkCollision(loc));
        int[][] table = area.getTable();
        table[1][1] = 1;
        assertTrue(area.checkCollision(loc));

    }

    /**
     * Test of getLocationValue method, of class GameArea.
     */
    @Test
    public void testGetLocationValue() {
        assertEquals(3, area.getLocationValue(0, 0));
        assertEquals(0, area.getLocationValue(1, 1));
        int[][] table = area.getTable();
        table[1][1] = 1;
        assertEquals(1, area.getLocationValue(1, 1));
    }

    /**
     * Test of isCorner method, of class GameArea.
     */
    @Test
    public void testIsCorner() {
        assertTrue(area.isCorner(1, 1));
        assertFalse(area.isCorner(2, 2));
        assertTrue(area.isCorner(1, area.getHeight() - 2));
        assertTrue(area.isCorner(area.getWidth() - 2, area.getWidth() - 2));
        assertTrue(area.isCorner(area.getWidth() - 2, 1));
    }

    /**
     * Test of clone method, of class GameArea.
     */
    @Test
    public void testClone() throws Exception {
    }

    /**
     * Test of placeApple method, of class GameArea.
     */
    @Test
    public void testPlaceApple() {
        Location loc = new Location(5, 5);
        assertFalse(area.checkApple(5, 5));
        area.placeApple(loc);
        assertTrue(area.checkApple(5, 5));
        
    }


    /**
     * Test of toString method, of class GameArea.
     */
    @Test
    public void testToString() {
    }


}
