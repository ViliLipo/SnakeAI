/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.game.Snake;

/**
 * 
 * @author vili
 */
public final class Survive {
    /**
     * Get one safe move for snake. Is used to make dying seem more sensible
     * @param snake
     * @return integer representing snakes directions.
     */
    public static int getSafeDirection(Snake snake) {
        try {
            Snake test = snake.clone();
            if (test.moveLeft()) {
                return Snake.LEFT;
            }
            test = snake.clone();
            if (test.moveRight()) {
                return Snake.RIGHT;
            }
            test = snake.clone();
            if (test.moveDown()) {
                return Snake.DOWN;
            }
            test = snake.clone();
            if (test.moveUp()) {
                return Snake.UP;
            }

        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return Snake.DOWN;
    }

}
