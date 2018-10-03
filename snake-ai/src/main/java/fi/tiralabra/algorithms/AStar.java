/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.BinaryHeap;
import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.datastructures.PathComparator;
import fi.tiralabra.datastructures.SnakeComparator;
import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;

/**
 *
 * @author vili
 */
public final class AStar {

    public static LinkedList<Location> path(Snake snake) {
        boolean[][] visited = new boolean[snake.getArea().getHeight()][snake.getArea().getHeight()];
        Location goal = MapTools.findApple(snake.getArea());
        BinaryHeap<Snake> snakeHeap = new BinaryHeap<>(100, new SnakeComparator(goal));
        BinaryHeap<LinkedList> pathHeap = new BinaryHeap<>(100, new PathComparator(goal));
        LinkedList<Location> p1 = new LinkedList<>();
        p1.add(snake.getHead());
        pathHeap.insert(p1);
        snakeHeap.insert(snake);
        while (!snakeHeap.isEmpty() && !pathHeap.isEmpty()) {
            Snake snek = snakeHeap.extract();
            LinkedList<Location> path = pathHeap.extract();
            if (snek.getHead().getX() == goal.getX() && snek.getHead().getY() == goal.getY()) {
                return path;
            }
            for (Snake cand : MapTools.getCandidates(snek)) {
                if (!visited[cand.getHead().getY()][cand.getHead().getX()]) {
                    visited[cand.getHead().getY()][cand.getHead().getX()] = true;
                    snakeHeap.insert(cand);
                    LinkedList<Location> candPath = new LinkedList<>();
                    candPath.addAll(path);
                    candPath.add(cand.getHead());
                    pathHeap.insert(candPath);
                }
            }
        }
        return null;
    }

}
