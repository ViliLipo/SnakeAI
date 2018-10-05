/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.ai;

import fi.tiralabra.algorithms.AStar;
import fi.tiralabra.algorithms.MapTools;
import fi.tiralabra.algorithms.Survive;
import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.Controller;
import fi.tiralabra.game.GameEngine;
import fi.tiralabra.game.Location;

/**
 *
 * @author vili
 */
public class AStarController implements Controller {

    private String method = "A*";
    private int timePassed = 0;
    private GameEngine engine;

    private LinkedList<Integer> directions;

    public AStarController(GameEngine ge) {
        this.engine = ge;
        this.directions = new LinkedList<>();
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public int getDirection() {
        if (!this.directions.isEmpty()) {
            int value = this.directions.poll();
            // System.out.println("value = " + value);
            return value;
        } else {
            long start = System.currentTimeMillis();
            LinkedList<Location> locPath = AStar.path(engine.getSnake());
            // System.out.println("GOT BFS PATH");
            if (locPath == null || locPath.isEmpty()) {
                return Survive.getSafeDirection(engine.getSnake());
            }
            this.directions = MapTools.locationPathToDirectionPath(locPath);
            this.timePassed = (int) (System.currentTimeMillis() - start);
            if (this.directions.isEmpty()) {
                System.out.println("SURVIVING");
                return Survive.getSafeDirection(engine.getSnake());
            }
            return this.directions.poll();
        }

    }

    @Override
    public int getTimePassed() {
        return this.timePassed;
    }

    @Override
    public String toString() {
        return this.method;
    }

    @Override
    public void reset() {
        this.directions = new LinkedList<>();
    }

}
