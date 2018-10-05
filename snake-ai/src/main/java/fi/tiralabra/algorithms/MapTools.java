/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.GameArea;
import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;
import java.util.Iterator;

/**
 *
 * @author vili
 */
public final class MapTools {
    
    /**
     * Returns the location of the apple
     * @param area
     * @return 
     */
    public static Location findApple(GameArea area) {
        int[][] map = area.getTable();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == GameArea.APPLE) {
                    return new Location(j, i);
                }
            }
        }
        return null;
    }

    /**
     * Convert path of locations to path of directions;
     *
     * @param path List of locations
     * @return List of integers representing directions
     */
    public static LinkedList<Integer> locationPathToDirectionPath(LinkedList<Location> path) {
        LinkedList<Integer> newPath = new LinkedList<Integer>();
        Iterator<Location> it = path.iterator();
        Location startPoint = path.getFirst();
        while (it.hasNext()) {
            Location loc = it.next();
            if (startPoint.getX() < loc.getX()) {
                newPath.add(Snake.RIGHT);
            } else if (startPoint.getX() > loc.getX()) {
                newPath.add(Snake.LEFT);
            } else if (startPoint.getY() < loc.getY()) {
                newPath.add(Snake.DOWN);
            } else if (startPoint.getY() > loc.getY()) {
                newPath.add(Snake.UP);
            }
            startPoint = loc;
        }
        return newPath;
    }
    /**
     * Get snakes that are viable next steps
     * @param snake
     * @return List of snakes
     */
    public static LinkedList<Snake> getCandidates(Snake snake) {
        LinkedList<Snake> candidates = new LinkedList<>();
        try {
            Snake up = snake.clone();
            if (up.moveUp()) {
                candidates.add(up);
            }
            Snake down = snake.clone();
            if (down.moveDown()) {
                candidates.add(down);
            }
            Snake right = snake.clone();
            if (right.moveRight()) {
                candidates.add(right);
            }
            Snake left = snake.clone();
            if (left.moveLeft()) {
                candidates.add(left);
            }
        } catch (CloneNotSupportedException ex) {
            System.out.println("NO CLONE");
        }
        return candidates;
    }

}
