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
    private Clock clock;
    private int gamerate = 30;
    private GameRenderer renderer;
    private Controller controller;
    private LinkedList<Integer> scores;

    public GameEngine(GameRenderer renderer) {
        this.area = new GameArea();
        this.reset();
        this.clock = new Clock(gamerate);
        this.renderer = renderer;
        this.controller = null;
        this.scores = new LinkedList<>();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Reset game to starting position
     */
    public final void reset() {
        this.area.reset();
        this.snake = new Snake(this.area, 5, 5);
        try {
            this.apple = new Apple(this.area);
        } catch (Exception ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GameArea getArea() {
        return this.area;
    }

    /**
     * Advance one game tick
     */
    public void cycle() {
        clock.startCycle();
        snake.turn(controller.getDirection());
        boolean done = !snake.move();
        if (snake.getGrow()) {
            try {
                this.apple = new Apple(area);
                System.out.println("Time passed: " + this.controller.getTimePassed() + "ms");
            } catch (Exception ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        renderer.renderGame(area);
        if (done) {
            System.out.println("SCORE:" + snake.getScore());
            scores.add(snake.getScore());
            System.out.println("AVG: " + this.avgScore());
            this.reset();
        }
        clock.endCycle();
    }

    public Snake getSnake() {
        return snake;
    }

    public double avgScore() {
        long sum = 0;
        for (int i : scores) {
            sum += i;
        }
        return (double) sum / scores.size();
    }

}
