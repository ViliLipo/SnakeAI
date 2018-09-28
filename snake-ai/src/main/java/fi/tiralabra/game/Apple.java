/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author vili
 */
public class Apple {

    private Location location;
    private GameArea area;

    /**
     * Create Apple and randomly place it in the GameArea
     *
     * @param area GameArea
     * @throws Exception if placing fails too many times, loop is broken and
     * exception is thrown.
     */
    public Apple(GameArea area) throws Exception {
        this.area = area;
        this.placeApple();
    }

    private void placeApple() throws Exception {
        int failurecount = 0;
        int failureMAX = 200;
        while (failurecount < failureMAX) {
            int x = ThreadLocalRandom.current().nextInt(1, this.area.getWidth() - 2);
            int y = ThreadLocalRandom.current().nextInt(1, this.area.getHeight() - 2);
            // System.out.println("x = " + x + " y = " + y);
            if (!this.area.isCorner(x, y) && !(this.area.checkCollision(x, y))) {
                this.location = new Location(x, y);
                this.area.placeApple(this.location);
                return;
            }
            failurecount++;
        }
        throw new Exception("Cannot place apple");
    }

    public Location getLocation() {
        return this.location;
    }
}
