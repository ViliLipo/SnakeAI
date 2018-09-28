/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.app;

import fi.tiralabra.ai.BFSController;
import fi.tiralabra.game.Controller;
import fi.tiralabra.game.GameEngine;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AppController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField algoText;

    @FXML
    private TextField avgTime;
    @FXML
    private TextField avgScore;
    @FXML
    private TextField newScoreValue;

    @FXML
    private Button runButton;
    private boolean running;
    private GameEngine ge;

    @FXML
    void runPressed(ActionEvent event) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                ge.cycle();
                algoText.setText("ALGORITHM:" + "BFS");
                avgScore.setText("Average score: "
                        + String.valueOf(ge.avgScore()));
                avgTime.setText("Average time per apple: " + String.valueOf(ge.avgTime())
                        + "ms");
                newScoreValue.setText("Current score: " + ge.getSnake().getScore());
            }
        }.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameRenderer gr = new CanvasGameRenderer(this.canvas);
        ge = new GameEngine(gr);
        Controller con = new BFSController(ge);
        ge.setController(con);
        running = false;
    }

}
