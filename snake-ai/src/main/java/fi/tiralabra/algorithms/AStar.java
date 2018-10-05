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

    private AStar() {
    }

    public static LinkedList<Location> path(Snake snake) {
        boolean[][] black = new boolean[snake.getArea().getHeight()][snake.getArea().getHeight()];
        boolean[][] gray = new boolean[snake.getArea().getHeight()][snake.getArea().getHeight()];
        int [][] gScores = new int[snake.getArea().getHeight()][snake.getArea().getHeight()];
        int [][] fScores = new int[snake.getArea().getHeight()][snake.getArea().getHeight()];
        LinkedList<Location>[][] paths = new LinkedList[snake.getArea().getHeight()][snake.getArea().getHeight()];
        for(int i = 0; i < snake.getArea().getHeight(); i++) {
            for(int j =0 ; j < snake.getArea().getHeight(); j++) {
                gScores[i][j] = Integer.MAX_VALUE;
                fScores[i][j] = Integer.MAX_VALUE;
            }
        }
        Location goal = MapTools.findApple(snake.getArea());
        BinaryHeap<AStarElement> primaryHeap = new BinaryHeap<>(100, new AStarComparator());
        LinkedList<Location> p1 = new LinkedList<>();
        p1.add(snake.getHead());
        int startX = snake.getHead().getX();
        int startY = snake.getHead().getY();
        AStarElement start = new AStarElement(snake, goal.distance(snake.getHead()), 0);
        gScores[startY][startX] = 0;
        fScores[startY][startX] = goal.distance(snake.getHead());
        paths[startY][startX] = p1;
        primaryHeap.insert(start);
        while (!primaryHeap.isEmpty()) {
            AStarElement current = primaryHeap.extract();
            Snake snek = current.getSnake();
            LinkedList<Location> path = paths[snek.getHead().getY()][snek.getHead().getX()];
            black[snek.getHead().getY()][snek.getHead().getX()] = true;
            if (snek.getHead().getX() == goal.getX() && snek.getHead().getY() == goal.getY()) {
                return path;
            }
            for (Snake cand : MapTools.getCandidates(snek)) {
                int candX = cand.getHead().getX();
                int candY = cand.getHead().getY();
                if (!black[candY][candX]) {
                    LinkedList<Location> candPath = new LinkedList<>();
                    candPath.addAll(path);
                    candPath.add(cand.getHead());
                    int gScore = current.getGscore() + 1;
                    if (gScore <= gScores[candY][candX]) {
                        paths[candY][candX] = candPath;
                        gScores[candY][candX] = gScore;
                        fScores[candY][candX] = gScore + goal.distance(cand.getHead());
                    }
                    if(!gray[candY][candX]) {
                        gray[candY][candX] = true;
                        AStarElement e = new AStarElement(cand, fScores[candY][candX], gScores[candY][candX]);
                        primaryHeap.insert(e);
                    }
                }
            }
        }
        return null;
    }

}
