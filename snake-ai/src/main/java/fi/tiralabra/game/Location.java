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
public class Location {
    private int x;
    private int y;
    private GameArea area;
    
    public Location(int x, int y, GameArea area) {
        this.x = x;
        this.y = y;
        this.area = area;
    }
    
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int toInt() {
        return (this.y * this.area.getWidth() + this.x);
    }
    
    
    
    
    
    
    
}
