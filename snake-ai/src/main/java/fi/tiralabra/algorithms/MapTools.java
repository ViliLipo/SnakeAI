/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.GameArea;
import fi.tiralabra.game.Location;
import java.util.Iterator;

/**
 *
 * @author vili
 */
public class MapTools {
    
    public static Location findApple(GameArea area) {
        int[][] map = area.getTable();
        for(int i =0; i < map.length; i++) {
            for(int j = 0; j< map[i].length; j ++) {
                if(map[i][j] == 2) {
                    return new Location(j, i, area );
                }
            }
        }
        return null;
    }
    
    public static LinkedList<Integer> locationPathToDirectionPath(LinkedList<Location> path) {
        LinkedList<Integer> newPath = new LinkedList<Integer>();
        Iterator<Location> it = path.iterator();
        Location startPoint = path.getFirst();
        while(it.hasNext()) {
            Location loc = it.next();
            if(startPoint.getX() < loc.getX()) {
                newPath.add(2);
            } else if( startPoint.getX() > loc.getX()) {
                newPath.add(-2);
            } else if (startPoint.getY() < loc.getY()) {
                newPath.add(-1);
            } else if (startPoint.getY() > loc.getY()) {
                newPath.add(1);
            }
            startPoint = loc;
        } 
        return newPath;
    }
    
}
