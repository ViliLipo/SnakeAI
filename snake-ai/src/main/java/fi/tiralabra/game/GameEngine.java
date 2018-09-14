/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

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
    
    
   public GameEngine() {
       this.area = new GameArea();
       this.snake = new Snake(this.area, 5, 5);
        try {
            this.apple = new Apple(this.area);
        } catch (Exception ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
    
}
