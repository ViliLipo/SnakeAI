/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.app;

import javafx.animation.AnimationTimer;
import fi.tiralabra.game.GameEngine;
import javafx.scene.control.TextField;

/**
 * This class is used as game clock
 * @author vili
 */
public class AppTimer extends AnimationTimer {

    private int speed;
    private boolean paused;
    private GameEngine ge;
    private TextField avgScore;
    private TextField avgTime;
    private TextField newScoreValue;
    private long lastUpdate;

    public AppTimer(int speed, GameEngine ge, TextField avgScore, TextField avgTime,
            TextField newScoreValue) {
        this.speed = speed;
        this.paused = false;
        this.ge = ge;
        this.avgScore = avgScore;
        this.avgTime = avgTime;
        this.newScoreValue = newScoreValue;

    }

    @Override
    public void handle(long now) {
        if (!paused && now - lastUpdate >= (100000000 / speed)) {
            ge.cycle();
            avgScore.setText("Average score: "
                    + String.format("%.2f", ge.avgScore()));
            avgTime.setText("Average time per apple: " + String.format("%.2f", ge.avgTime())
                    + "ms");
            newScoreValue.setText("Current score: " + ge.getSnake().getScore());
            lastUpdate = now;
        }
    }
    /**
     * Set the game tickrate
     * @param i int the tickrate
     */
    public void setSpeed(int i) {
        this.speed = i;
    }
    /**
     * Pause the game if not paused and vice versa.
     */
    public void togglePause() {
        this.paused = !this.paused;
    }
    /**
     * 
     * @return boolean is the game paused
     */
    public boolean getPaused() {
        return this.paused;
    }

}
