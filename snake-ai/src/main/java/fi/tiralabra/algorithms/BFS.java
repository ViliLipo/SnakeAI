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
 * This Class provides static path-method. Uses BFS
 * @author vili
 */
public final class BFS {

    private BFS() {

    }

    /**
     * Get a path of locations for snake using Breath first search
     *
     * @param snake Snake at the starting point.
     * @return List of locations starting for given snakes head ending to apple
     * If no such path exist this method will return null.
     */
    public static LinkedList<Location> path(Snake snake) {
        boolean[][] visited = new boolean[snake.getArea().getHeight()][snake.getArea().getWidth()];
        Location[][] cameFrom = new Location[snake.getArea().getHeight()][snake.getArea().getWidth()];
        LinkedList<Snake> snakeQueue = new LinkedList<>();
        snakeQueue.add(snake);
        visited[snake.getHead().getY()][snake.getHead().getX()] = true;
        Location end = MapTools.findApple(snake.getArea());
        while (!snakeQueue.isEmpty()) {
            Snake snek = snakeQueue.poll();
            Location node = snek.getHead();
            if (node.getX() == end.getX() && node.getY() == end.getY()) {
                return buildPath(end, cameFrom);
            }
            for (Snake candidate : MapTools.getCandidates(snek)) {
                if (!visited[candidate.getHead().getY()][candidate.getHead().getX()]) {
                    visited[candidate.getHead().getY()][candidate.getHead().getX()] = true;
                    cameFrom[candidate.getHead().getY()][candidate.getHead().getX()] = snek.getHead();
                    snakeQueue.add(candidate);
                }
            }

        }
        return null;
    }

    private static LinkedList<Location> buildPath(Location end, Location[][] cameFrom) {
        Location index = end;
        LinkedList<Location> path = new LinkedList<>();
        while (index != null) {
            path.addFirst(index);
            index = cameFrom[index.getY()][index.getX()];
        }
        return path;
    }
}
