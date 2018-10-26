/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.Apple;
import fi.tiralabra.game.DeterministicApple;
import fi.tiralabra.game.GameArea;
import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vili
 */
public class AStarTest {

    public AStarTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of path method.
     */
    @Test
    public void testPath() {
        GameArea ga = new GameArea();
        Snake snake = new Snake(ga, 5, 5);
        ga.getTable()[8][8] = 2;
        snake.setArea(ga);
        AStar aStar = new AStar(snake);
        LinkedList<Location> path = aStar.path();
        assertEquals(7, path.size());
    }

    /**
     * Test path method with more complex set of apples, that ensure that the
     * snake wont hit itself.
     */
    @Test
    public void testComplexPath() {
        GameArea ga = new GameArea();
        Snake snake = new Snake(ga, 5, 5);
        Apple apple = new DeterministicApple(ga);
        for (int i = 0; i < 25; i++) {
            try {
                apple.placeApple();
                AStar aStar = new AStar(snake);
                LinkedList<Location> path = aStar.path();
                LinkedList<Integer> inst = MapTools.locationPathToDirectionPath(path, ga);
                for (int direction : inst) {
                    snake.turn(direction);
                    assertTrue(snake.move());
                }
                assertTrue(snake.getGrow());
            } catch (Exception ex) {
                ex.printStackTrace();
                assertTrue("There was an exception",false);
            }

        }
    }

}
