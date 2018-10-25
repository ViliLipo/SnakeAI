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
        this.apple = new Apple(this.area);
        this.reset();
        this.renderer = renderer;
        this.controller = null;
        this.scores = new LinkedList<>();
        timepassed = 0;
        tickcount = 0;
        deathCount = 0;
    }
    /**
     * Set the controller for snake. Use this before calling cycle.
     * @param controller, Object that implements controller interface
     */
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
        if (this.controller != null) {
            this.controller.reset();
        }
        try {
            this.apple.placeApple();
        } catch (Exception ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Advance one game tick, set controller before calling this.
     */
    public void cycle() {
        snake.turn(controller.getDirection());
        boolean done = !snake.move();
        if (snake.getGrow()) {
            try {
                this.apple.placeApple();
                this.timepassed += this.controller.getTimePassed();
                this.tickcount++;
            } catch (Exception cantplaceapple) {
                done = true;
            }
        }
        renderer.renderGame(area);
        if (done) {
            scores.add(snake.getScore());
            this.deathCount++;
            this.reset();
        }
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
        return this.timepassed / (double) this.tickcount;
    }

    public long getDeathCount() {
        return this.deathCount;
    }
}
