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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vili
 */
public class BFS {
    
    public static LinkedList<Location> path(Snake snake) {
        boolean[][] visited = new boolean[snake.getArea().getHeight()][snake.getArea().getWidth()];
        LinkedList<LinkedList> pathQueue = new LinkedList<>();
        LinkedList<Snake> snakeQueue = new LinkedList<>();
        LinkedList<Location> firstPath = new LinkedList<>();
        firstPath.add(snake.getHead());
        pathQueue.add(firstPath);
        snakeQueue.add(snake);
        visited[snake.getHead().getY()][snake.getHead().getX()] = true;
        Location end = MapTools.findApple(snake.getArea());
        while(!pathQueue.isEmpty()) {
            LinkedList<Location> path = pathQueue.poll();
            Location node = path.getLast();
            Snake snek = snakeQueue.poll();
            if(node.getX() == end.getX() && node.getY() == end.getY()) {
                return path;
            }
            for(Snake candidate: getCandidates(snek)) {
                if(!visited[candidate.getHead().getY()][candidate.getHead().getX()]) {
                    visited[candidate.getHead().getY()][candidate.getHead().getX()] = true;
                    LinkedList<Location> newPath = new LinkedList<>();
                    newPath.addAll(path);
                    newPath.add(candidate.getHead());
                    snakeQueue.add(candidate);
                    pathQueue.add(newPath);
                }
            }
            
        }
        return null;
    }
    
    private static LinkedList<Snake> getCandidates(Snake snake) {
        LinkedList<Snake> candidates = new LinkedList<>();
        try {
            Snake up = snake.clone();
            if(up.moveUp()) {
                candidates.add(up);
            }
            Snake down = snake.clone();
            if(down.moveDown()) {
                candidates.add(down);
            }
            Snake right = snake.clone();
            if(right.moveRight()) {
                candidates.add(right);
            }
            Snake left = snake.clone();
            if(left.moveLeft()) {
                candidates.add(left);
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BFS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return candidates;
    }


}
