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
        assertFalse(this.snake.getGrow());
        this.area.getTable()[5][6] = GameArea.APPLE;
        snake.move();
        assertTrue(this.snake.getGrow());

    }

    /**
     * Test of getScore method, of class Snake.
     */
    @Test
    public void testGetScore() {
        assertEquals(0, snake.getScore());
        this.area.getTable()[5][6] = GameArea.APPLE;
        snake.move();
        assertEquals(10, snake.getScore());
    }

    /**
     * Test of getHead method, of class Snake.
     */
    @Test
    public void testGetHead() {
        Location head = snake.getHead();
        assertEquals(5, head.getX());
        assertEquals(5, head.getY());
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
        snake.setArea(this.area);
        assertEquals(this.area, snake.getArea());
    }

    /**
     * Test of getArea method, of class Snake.
     */
    @Test
    public void testGetArea() {
        assertEquals(this.area, snake.getArea());
    }

}
