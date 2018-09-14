/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.app;

import fi.tiralabra.game.GameArea;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author vili
 */
public class CanvasGameRenderer implements GameRenderer {

    private Canvas canvas;

    public CanvasGameRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void renderGame(GameArea area) {
        int blockx = (int) Math.floor(canvas.getWidth() / (double) area.getWidth());
        int blocky = (int) Math.floor(canvas.getHeight() / (double) area.getHeight());
        int xp = 0;
        int yp = 0;
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(),
                canvas.getHeight());
        for (int y = 0; y < area.getHeight(); y++) {
            for (int x = 0; x < area.getWidth(); x++) {
                int value = area.getLocationValue(x, y);
                gc.setFill(determineColor(value));
                gc.fillRect(xp, yp, blockx, blocky);
                xp += blockx;
            }
            yp += blocky;
            xp = 0;
        }
    }

    private Color determineColor(int value) {
        switch (value) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.RED;
            case 3:
                return Color.GRAY;
            default:
                return Color.BLACK;
        }
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
