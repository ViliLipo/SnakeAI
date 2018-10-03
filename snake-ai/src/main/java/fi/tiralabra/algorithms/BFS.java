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
public final class BFS {

    private BFS() {

    }

    /**
     * Get a path of locations for snake using Breath first search
     *
     * @param snake
     * @return List of locations starting for given snakes head ending to apple
     * If no such path exist this method will return null.
     */
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
        while (!pathQueue.isEmpty()) {
            LinkedList<Location> path = pathQueue.poll();
            Location node = path.getLast();
            Snake snek = snakeQueue.poll();
            if (node.getX() == end.getX() && node.getY() == end.getY()) {
                return path;
            }
            for (Snake candidate : MapTools.getCandidates(snek)) {
                if (!visited[candidate.getHead().getY()][candidate.getHead().getX()]) {
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

    /**
     * Do not use, work in progress
     *
     * @param snake
     * @return
     */
    public static LinkedList<Location> exhaustiveSafePath(Snake snake) {
        boolean[][] visited = new boolean[snake.getArea().getHeight()][snake.getArea().getWidth()];
        LinkedList<LinkedList> pathQueue = new LinkedList<>();
        LinkedList<Snake> snakeQueue = new LinkedList<>();
        LinkedList<Location> firstPath = new LinkedList<>();
        firstPath.add(snake.getHead());
        pathQueue.add(firstPath);
        snakeQueue.add(snake);
        visited[snake.getHead().getY()][snake.getHead().getX()] = true;
        Location end = MapTools.findApple(snake.getArea());
        while (!pathQueue.isEmpty()) {
            LinkedList<Location> path = pathQueue.poll();
            Location node = path.getLast();
            Snake snek = snakeQueue.poll();
            if (node.getX() == end.getX() && node.getY() == end.getY()) {
                if (validate(snek, 50)) {
                    return path;
                } else {
                    visited[node.getY()][node.getX()] = false;
                    System.out.println("REJECTED PATH");
                }
            }
            for (Snake candidate : MapTools.getCandidates(snek)) {
                if (!visited[candidate.getHead().getY()][candidate.getHead().getX()]) {
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

    private static boolean validate(Snake snake, int limit) {
        return (surviveCount(snake, 0, limit) >= limit);
    }

    private static int surviveCount(Snake snake, int count, int limit) {
        count++;
        if (count >= limit) {
            return count;
        }
        LinkedList<Snake> cands = MapTools.getCandidates(snake);
        int newCount = count;
        for (Snake snek : cands) {
            newCount = Integer.max(newCount, surviveCount(snek, count, limit));
            if (newCount >= limit) {
                return newCount;
            }
        }
        return newCount;
    }

}
