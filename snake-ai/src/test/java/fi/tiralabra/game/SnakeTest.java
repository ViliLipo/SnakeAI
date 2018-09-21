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
public class SnakeTest {

    private Snake snake;
    private GameArea area;

    public SnakeTest() {
    }

    @Before
    public void setUp() {
        this.area = new GameArea();
        this.snake = new Snake(this.area, 5, 5);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of turn method, of class Snake.
     */
    @Test
    public void testTurn() {
        snake.turn(1);
        snake.turn(-2);
        assertEquals(-2, snake.getDirection());
        snake.turn(2);
        assertEquals(-2, snake.getDirection());
        snake.turn(1);
        assertEquals(1, snake.getDirection());
        snake.turn(-1);
        assertEquals(1, snake.getDirection());
    }

    /**
     * Test of move method, of class Snake.
     */
    @Test
    public void testMove() {
        int xval = snake.getHead().getX() + 1;
        int yval = snake.getHead().getY();
        assertTrue(snake.move());
        assertEquals(xval, snake.getHead().getX());
        assertEquals(yval, snake.getHead().getY());
        snake.turn(Snake.DOWN);
        assertTrue(snake.move());
        yval += 1;
        assertEquals(xval, snake.getHead().getX());
        assertEquals(yval, snake.getHead().getY());
        snake.turn(Snake.LEFT);
        snake.move();
        xval -= 1;
        assertEquals(xval, snake.getHead().getX());
        assertEquals(yval, snake.getHead().getY());
        snake.turn(Snake.UP);
        snake.move();
        yval -= 1;
        assertEquals(xval, snake.getHead().getX());
        assertEquals(yval, snake.getHead().getY());
    }

    /**
     * Test of getGrow method, of class Snake.
     */
    @Test
    public void testGetGrow() {
    }

    /**
     * Test of getScore method, of class Snake.
     */
    @Test
    public void testGetScore() {
    }

    /**
     * Test of getHead method, of class Snake.
     */
    @Test
    public void testGetHead() {
    }

    /**
     * Test of clone method, of class Snake.
     */
    @Test
    public void testClone() throws Exception {
    }

    /**
     * Test of setArea method, of class Snake.
     */
    @Test
    public void testSetArea() {
    }

    /**
     * Test of getArea method, of class Snake.
     */
    @Test
    public void testGetArea() {
    }

}
