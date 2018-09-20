/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.GameArea;
import fi.tiralabra.game.Location;

/**
 *
 * @author vili
 */
public class BFS {

    public static LinkedList<Location> path(GameArea area, Location loc) {
        boolean[][] visited = new boolean[area.getHeight()][area.getWidth()];
        LinkedList<LinkedList> queue = new LinkedList<>();
        LinkedList<Location> path = new LinkedList<>();
        path.add(loc);
        queue.add(path);
        visited[loc.getY()][loc.getX()] = true;
        Location end = MapTools.findApple(area);
        System.out.println("START x:" + loc.getX()+ " y :" + loc.getY());
        System.out.println("END x:" + end.getX() + " y: " + end.getY());
        while (!queue.isEmpty()) {
            //System.out.println("BFS LOOP");
            LinkedList<Location> p1 = queue.poll();
            Location node = p1.getLast();
            visited[node.getY()][node.getX()] = true;
            //System.out.println("NOW AT x:" + node.getX() + " y:" + node.getY());
            if (node.getX() == end.getX() && node.getY() == end.getY()) {
                return p1;
            }
            for (Location adjacent : getAdjacent(area, node)) {
                if (!visited[adjacent.getY()][adjacent.getX()]) {
                    LinkedList<Location> newPath = new LinkedList<>();
                    newPath.addAll(p1);
                    newPath.add(adjacent);
                    queue.add(newPath);
                    visited[adjacent.getY()][adjacent.getX()] = true;
                    //System.out.println("ADDED x: " + adjacent.getX() + " y:" +adjacent.getY());
                }
            }
        }
        return null;
    }

    private static LinkedList<Location> getAdjacent(GameArea area, Location loc) {
        LinkedList<Location> adjacent = new LinkedList<>();
        LinkedList<Location> candidates = new LinkedList<>();
        candidates.add(loc.getTop());
        candidates.add(loc.getRight());
        candidates.add(loc.getBottom());
        candidates.add(loc.getLeft());
        for (Location l : candidates) {
            if (l.validate()) {
                int value = area.getLocationValue(l.getX(), l.getY());
                if (value != 3 && value != 1) {
                    adjacent.add(l);
                }
            }
        }
        return adjacent;
    }

}
