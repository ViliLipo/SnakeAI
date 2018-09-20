/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.app.CanvasGameRenderer;
import fi.tiralabra.app.GameRenderer;
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
    private int gamerate = 60;
    private GameRenderer renderer;
    private Controller controller;

    public GameEngine(GameRenderer renderer) {
        this.area = new GameArea();
        this.reset();
        this.clock = new Clock(gamerate);
        this.renderer = renderer;
        this.controller = null;
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
        boolean done = snake.move();
        if (snake.getGrow()) {
            try {
                this.apple = new Apple(area);
            } catch (Exception ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        renderer.renderGame(area);
        System.out.println(snake.getScore());
        if (done) {
            this.reset();
        }
        clock.endCycle();
    }
    public Snake getSnake() {
        return snake;
    }

}
