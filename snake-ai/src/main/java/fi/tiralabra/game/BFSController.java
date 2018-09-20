/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.algorithms.BFS;
import fi.tiralabra.algorithms.MapTools;
import fi.tiralabra.datastructures.LinkedList;

/**
 *
 * @author vili
 */
public class BFSController implements Controller{
    
    private LinkedList<Integer> directions;
    private GameArea area;
    private GameEngine engine;
    
    public BFSController(GameEngine engine) {
        this.engine = engine;
        this.area = engine.getArea();
        this.directions = new LinkedList<Integer>();
    }
    

    @Override
    public int getDirection() {
        if(!this.directions.isEmpty()) {
            int value = this.directions.poll();
            System.out.println("value = " + value);
            return value;
        } else {
            LinkedList<Location> locPath = BFS.path(area, engine.getSnake().getHead());
            System.out.println("GOT BFS PATH");
            if(locPath == null) {
                return -1;
            }
            this.directions = MapTools.locationPathToDirectionPath(locPath);
            return this.directions.poll();
        }
        
    }
    
}
