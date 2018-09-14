/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

/**
 *
 * @author vili
 */
public interface Controller {
    /**
     * 
     * @return int representing direction
     * 1 = UP, -1 = DOWN, 2=RIGHT, -2=LEFT
     */
    public int getDirection();
}
