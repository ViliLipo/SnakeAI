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
        assertTrue("Average time should be less than 17ms", this.engine.avgTime() <= 17);
        assertTrue("Average score should be more than 1200", this.engine.avgScore() >= 1200);
        System.out.println("avgtime = " + this.engine.avgTime());
        System.out.println("avgScore = " + this.engine.avgScore());
    }

    @Test
    public void testCycleWithBfs() {
        this.engine.setController(new BFSController(this.engine));
        System.out.println("cycle with bfs");
        for (int i = 0; i < 1_00_000; i++) {
            this.engine.cycle();
        }
        assertTrue("Average time should be less than 30ms", this.engine.avgTime() <= 30);
        assertTrue("Average score should be more than 1200", this.engine.avgScore() >= 1400);
        System.out.println("avgtime = " + this.engine.avgTime());
        System.out.println("avgScore = " + this.engine.avgScore());
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
