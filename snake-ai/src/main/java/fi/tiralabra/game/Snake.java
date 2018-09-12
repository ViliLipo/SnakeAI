/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.datastructures.LinkedList;

/**
 *
 * @author vili
 */
public class Snake {
    
    private LinkedList<Location> locations;
    
    private GameArea area;
    
    public Snake(GameArea area) {
        this.area = area;
    }
    
    
    
    public boolean moveUp() {
        return false;
    }
    
    public boolean moveDown() {
        return false;
    }
    
    public boolean moveRight() {
        return false;
    }
    
    public boolean moveLeft() {
        return false;
    }
    
    
    
    
    
    
    
}
