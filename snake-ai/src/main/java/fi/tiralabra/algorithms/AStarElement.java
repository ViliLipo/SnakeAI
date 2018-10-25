/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;

/**
 * Class that is used to simulate AStar with heap
 * @author vili
 */
public class AStarElement {
    private Snake snake;
    private int fScore;
    private boolean valid;
    
    public AStarElement(Snake snake, int fScore) {
        this.snake = snake;
        this.fScore = fScore;
        this.valid = true;
    }
    
    public int getFscore() {
        return this.fScore;
    }
    public void setFscore(int score) {
        this.fScore = score;
    }
    public Snake getSnake() {
        return this.snake;
    }
    public void setValid(boolean bo) {
        this.valid = bo;
    }
    
    public boolean isValid() {
        return this.valid;
    }
    
}
