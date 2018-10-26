/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

/**
 * Interface for controlling snakes
 * @author vili
 */
public interface Controller {

    /**
     *
     * @return int representing direction 1 = UP, -1 = DOWN, 2=RIGHT, -2=LEFT
     */
    public int getDirection();

    /**
     * Get method of creating directions
     *
     * @return String describing method
     */
    public String getMethod();

    /**
     * Get time passed on solving path to apple
     *
     * @return Integer time passed in milliseconds
     */
    public int getTimePassed();

    /**
     * reset this controller to begin state
     */
    public void reset();

}
