/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.ai;

import fi.tiralabra.algorithms.BFS;
import fi.tiralabra.algorithms.MapTools;
import fi.tiralabra.datastructures.LinkedList;
import fi.tiralabra.game.Controller;
import fi.tiralabra.game.GameArea;
import fi.tiralabra.game.GameEngine;
import fi.tiralabra.game.Location;
import fi.tiralabra.algorithms.Survive;

/**
 *
 * @author vili
 */
public class BFSController implements Controller {

    private LinkedList<Integer> directions;
    private GameEngine engine;
    private final String method = "Breadth first search";
    private int timePassed;

    public BFSController(GameEngine engine) {
        this.engine = engine;
        this.directions = new LinkedList<>();
        this.timePassed = 0;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public int getDirection() {
        if (!this.directions.isEmpty()) {
            int value = this.directions.poll();
            return value;
        } else {
            long start = System.currentTimeMillis();
            LinkedList<Location> locPath = BFS.path(engine.getSnake());
            if (locPath == null || locPath.isEmpty()) {
                return Survive.getSafeDirection(engine.getSnake());
            }
            this.directions = MapTools.locationPathToDirectionPath(locPath);
            this.timePassed = (int) (System.currentTimeMillis() - start);
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
