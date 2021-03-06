/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.BinaryHeap;
import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.Location;
import fi.tiralabra.game.Snake;

/**
 * This object is used to create paths using AStar algorithm.
 * 
 * @author vili
 */
public class AStar {

    private final Snake snake;
    private final boolean[][] black;
    private final boolean[][] gray;
    private final int[][] gScores;
    private final int[][] fScores;
    private final AStarElement[][] elementMap;
    private final Location[][] cameFrom;
    private final Location goal;
    private final BinaryHeap<AStarElement> primaryHeap;
    
/**
 * This constructor sets up AStar for one path.
 * Make new AStar for each path
 * @param snake Snake at the starting point
 */
    public AStar(Snake snake) {
        this.snake = snake;
        int height = snake.getArea().getHeight();
        int width = snake.getArea().getWidth();
        black = new boolean[height][width];
        gray = new boolean[height][width];
        gScores = new int[height][width];
        fScores = new int[height][width];
        elementMap = new AStarElement[height][width];
        cameFrom = new Location[height][width];
        initSingleSource(gScores, fScores, snake);
        this.goal = MapTools.findApple(snake.getArea());
        primaryHeap = new BinaryHeap<>(100, new AStarComparator());
    }

    /**
     * Get a path from snakes head to apple. The path is generated using AStar
     *
     * 
     * @return LinkedList of locations on the path, empty list if no path is
     * found
     */
    public LinkedList<Location> path() {
        int startX = snake.getHead().getX();
        int startY = snake.getHead().getY();
        AStarElement start = new AStarElement(snake, goal.distance(snake.getHead()));
        gScores[startY][startX] = 0;
        fScores[startY][startX] = goal.distance(snake.getHead());
        gray[startY][startX] = true;
        cameFrom[startY][startX] = null;
        primaryHeap.insert(start);
        while (!primaryHeap.isEmpty()) {
            AStarElement current = primaryHeap.extract();
            if (!current.isValid()) { // this element in heap has been relaxed
                continue;
            }
            Snake snek = current.getSnake();
            black[snek.getHead().getY()][snek.getHead().getX()] = true;
            if (snek.getHead().getX() == goal.getX() && snek.getHead().getY() == goal.getY()) {
                return buildPath(snek.getHead(), cameFrom);
            }
            for (Snake cand : MapTools.getCandidates(snek)) {
                // do not change to stream because of the overhead
                this.handleNeighbor(cand, snek);
            }
        }
        return new LinkedList<>();
    }
    /**
     * Handle one neighbor for current snake
     * @param neighbor the neighbor
     * @param current current snake
     */
    private void handleNeighbor(Snake neighbor, Snake current) {
        int candX = neighbor.getHead().getX();
        int candY = neighbor.getHead().getY();
        if (!black[candY][candX]) {
            int gScore = gScores[current.getHead().getY()][current.getHead().getX()] + 1;
            if (gScore < gScores[candY][candX] | !gray[candY][candX]) {
                gScores[candY][candX] = gScore;
                fScores[candY][candX] = gScore + goal.distance(neighbor.getHead());
                cameFrom[candY][candX] = current.getHead();
                if (!gray[candY][candX]) {
                    gray[candY][candX] = true;
                    AStarElement e = new AStarElement(neighbor, fScores[candY][candX]);
                    primaryHeap.insert(e);
                    elementMap[candY][candX] = e;
                } else {
                    AStarElement e = elementMap[candY][candX];
                    e.setValid(false);
                    e = new AStarElement(neighbor, fScores[candY][candX]);
                    primaryHeap.insert(e);
                    elementMap[candY][candX] = e;
                }
            }
        }

    }

    private static void initSingleSource(int[][] fScores, int[][] gScores, Snake snake) {

        for (int i = 0; i < snake.getArea().getHeight(); i++) {
            for (int j = 0; j < snake.getArea().getWidth(); j++) {
                gScores[i][j] = Integer.MAX_VALUE;
                fScores[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static LinkedList<Location> buildPath(Location goal, Location[][] cameFrom) {
        LinkedList<Location> path = new LinkedList<>();
        Location index = goal;
        while (index != null) {
            path.addFirst(index);
            index = cameFrom[index.getY()][index.getX()];
        }
        return path;
    }

}
