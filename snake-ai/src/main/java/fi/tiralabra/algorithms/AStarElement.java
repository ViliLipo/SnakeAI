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
 *
 * @author vili
 */
public class AStarElement {
    private Snake snake;
    private int fScore;
    private int gScore;
    
    public AStarElement(Snake snake, int fScore, int gScore) {
        this.snake = snake;
        this.fScore = fScore;
        this.gScore = gScore;
    }
    
    public int getFscore() {
        return this.fScore;
    }
    public int getGscore() {
        return this.gScore;
    }
    public Snake getSnake() {
        return this.snake;
    }
}
