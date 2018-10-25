/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.ai.AStarController;
import fi.tiralabra.ai.BFSController;
import fi.tiralabra.app.GameRenderer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vili
 */
public class GameEngineTest {

    private GameEngine engine;

    public GameEngineTest() {
    }

    private class mockRenderer implements GameRenderer {

        @Override
        public void renderGame(GameArea area) {
        }

        @Override
        public void reset() {
        }

    }

    @Before
    public void setUp() {
        this.engine = new GameEngine(new mockRenderer());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of cycle method, of class GameEngine.
     */
    @Test
    public void testCycleWithAStar() {
        this.engine.setController(new AStarController(this.engine));
        System.out.println("cycle with astar");
        for (int i = 0; i < 1_00_000; i++) {
            this.engine.cycle();
        }
        assertTrue(this.engine.avgTime() <= 17);
        assertTrue(this.engine.avgScore() >= 1200);
    }

    @Test
    public void testCycleWithBfs() {
        this.engine.setController(new BFSController(this.engine));
        System.out.println("cycle with bfs");
        while(this.engine.getDeathCount() < 100) {
            this.engine.cycle();
        }
        assertTrue(this.engine.avgTime() <= 30);
        assertTrue(this.engine.avgScore() >= 1400);
    }

    /**
     * Test of avgScore method, of class GameEngine.
     */
    @Test
    public void testAvgScore() {
        System.out.println("avgScore");
        GameEngine instance = this.engine;
        double expResult = 0.0;
        double result = instance.avgScore();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of avgTime method, of class GameEngine.
     */
    @Test
    public void testAvgTime() {
        System.out.println("avgTime");
        GameEngine instance = this.engine;
        double expResult = 0.0;
        double result = instance.avgTime();
        assertEquals(expResult, result, 0.0);
    }

}
