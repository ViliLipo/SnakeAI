/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.app.GameRenderer;
import fi.tiralabra.datastructures.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vili
 */
public class GameEngine {

    private GameArea area;
    private Snake snake;
    private Apple apple;
    private GameRenderer renderer;
    private Controller controller;
    private LinkedList<Integer> scores;
    private long timepassed;
    private long tickcount;
    private long deathCount;

    public GameEngine(GameRenderer renderer) {
        this.area = new GameArea();
        this.reset();
        this.renderer = renderer;
        this.controller = null;
        this.scores = new LinkedList<>();
        timepassed = 0;
        tickcount = 0;
        deathCount = 0;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        this.tickcount = 0;
        this.timepassed = 0;
    }

    /**
     * Reset game to starting position
     */
    public final void reset() {
        this.area.reset();
        this.snake = new Snake(this.area, 5, 5);
        if(this.controller != null) {
            this.controller.reset();
        }
        try {
            this.apple = new Apple(this.area);
        } catch (Exception ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Advance one game tick
     */
    public void cycle() {
        snake.turn(controller.getDirection());
        boolean done = !snake.move();
        if (snake.getGrow()) {
            try {
                this.apple = new Apple(area);
                this.timepassed += this.controller.getTimePassed();
                this.tickcount++;
            } catch (Exception ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        renderer.renderGame(area);
        if (done) {
            scores.add(snake.getScore());
            this.deathCount++;
            this.reset();
        }
        // clock.endCycle();
    }

    public Snake getSnake() {
        return snake;
    }

    public double avgScore() {
        long sum = 0;
        if (scores.isEmpty()) {
            return 0;
        }
        for (int i : scores) {
            sum += i;
        }
        return (double) sum / scores.size();
    }

    public double avgTime() {
        if (this.tickcount == 0) {
            return 0;
        }
        return this.timepassed / (double)this.tickcount;
    }
    public long getDeathCount() {
        return this.deathCount;
    }
}
