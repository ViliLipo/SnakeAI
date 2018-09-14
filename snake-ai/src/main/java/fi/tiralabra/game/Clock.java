/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

/**
 *
 * @author vili
 */
public class Clock {

    private long time;
    private long delta;

    public Clock(int rate) {
        this.time = System.currentTimeMillis();
        this.delta = Math.floorDiv(1, rate);
    }

    public void startCycle() {
        this.time = System.currentTimeMillis();
    }

    public void endCycle() {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - this.time;
        if (this.delta < timePassed) {
            try {
                Thread.sleep(this.delta - timePassed);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
