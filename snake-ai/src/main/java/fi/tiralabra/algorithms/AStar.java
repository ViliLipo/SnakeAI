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
 *
 * @author vili
 */
public final class AStar {

    private AStar() {
    }

    /**
     * Get a path from snakes head to apple. The path is generated using AStar
     *
     * @param snake, snake to guide
     * @return LinkedList of locations on the path, empty list if no path is
     * found
     */
    public static LinkedList<Location> path(Snake snake) {
        int height = snake.getArea().getHeight();
        int width = snake.getArea().getWidth();
        boolean[][] black = new boolean[height][width];
        boolean[][] gray = new boolean[height][width];
        int[][] gScores = new int[height][width];
        int[][] fScores = new int[height][width];
        AStarElement[][] elementMap = new AStarElement[height][width];
        Location[][] cameFrom = new Location[height][width];
        initSingleSource(gScores, fScores, snake);
        Location goal = MapTools.findApple(snake.getArea());
        BinaryHeap<AStarElement> primaryHeap = new BinaryHeap<>(100, new AStarComparator());
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
            if(!current.isValid()) { // this element in heap has been relaxed
                continue;
            }
            Snake snek = current.getSnake();
            black[snek.getHead().getY()][snek.getHead().getX()] = true;
            if (snek.getHead().getX() == goal.getX() && snek.getHead().getY() == goal.getY()) {
                return buildPath(snek.getHead(), cameFrom);
            }
            for (Snake cand : MapTools.getCandidates(snek)) {
                int candX = cand.getHead().getX();
                int candY = cand.getHead().getY();
                if (!black[candY][candX]) {
                    int gScore = gScores[snek.getHead().getY()][snek.getHead().getX()] + 1;
                    if (gScore < gScores[candY][candX] | !gray[candY][candX]) {
                        gScores[candY][candX] = gScore;
                        fScores[candY][candX] = gScore + goal.distance(cand.getHead());
                        cameFrom[candY][candX] = snek.getHead();
                        if (!gray[candY][candX]) {
                            gray[candY][candX] = true;
                            AStarElement e = new AStarElement(cand, fScores[candY][candX]);
                            primaryHeap.insert(e);
                            elementMap[candY][candX] = e;
                        }else {
                            AStarElement e = elementMap[candY][candX];
                            e.setValid(false);
                            e = new AStarElement(cand, fScores[candY][candX]);
                            primaryHeap.insert(e);
                            elementMap[candY][candX] = e;
                        }
                    }
                }
            }
        }
        return new LinkedList<>();
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
