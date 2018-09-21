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
        this.delta = (long) Math.floor((1.d / rate) * 1000);
    }

    /**
     * Marks beginning of new cycle
     */
    public void startCycle() {
        this.time = System.currentTimeMillis();
    }

    /**
     * Ends cycle. if cycle was ready early waits until cycle has lasted delta
     * amount of time.
     */
    public void endCycle() {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - this.time;
        if (this.delta > timePassed) {
            try {
                Thread.sleep(this.delta - timePassed);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
