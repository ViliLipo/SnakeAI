/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.Apple;
import fi.tiralabra.game.DetermisticApple;
import fi.tiralabra.game.GameArea;
import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;
import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vili
 */
public class BFSTest {

    public BFSTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of path method, of class BFS.
     */
    @Test
    public void testPath() {
        GameArea ga = new GameArea();
        Snake snake = new Snake(ga, 5, 5);
        ga.getTable()[8][8] = 2;
        snake.setArea(ga);
        LinkedList<Location> path = BFS.path(snake);
        LinkedList<Location> expectedPath = new LinkedList<>();
        for (int i = 5; i <= 8; i++) {
            path.add(new Location(5, i));
        }
        for (int i = 5; i <= 8; i++) {
            path.add(new Location(i, 8));
        }
        Iterator<Location> it1 = path.iterator();
        Iterator<Location> it2 = expectedPath.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Location loc1 = it1.next();
            Location loc2 = it2.next();
            assertEquals(loc2.getX(), loc1.getX());
            assertEquals(loc2.getY(), loc1.getY());
        }
    }
    
      /**
     * Test path method with more complex set of apples, that ensure
     * that the snake wont hit itself.
     */
    @Test
    public void testComplexPath() {
        GameArea ga = new GameArea();
        Snake snake = new Snake(ga, 5, 5);
        Apple apple = new DetermisticApple(ga);
        for (int i = 0; i < 25; i++) {
            try {
                apple.placeApple();
                LinkedList<Location> path = BFS.path(snake);
                LinkedList<Integer> inst = MapTools.locationPathToDirectionPath(path);
                for(int direction : inst) {
                    snake.turn(direction);
                    assertTrue(snake.move());
                }
                assertTrue(snake.getGrow());
            } catch (Exception ex){
                assertTrue(false);
            }

        }
    }

}
