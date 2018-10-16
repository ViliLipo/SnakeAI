/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.app;

import fi.tiralabra.ai.AStarController;
import fi.tiralabra.ai.BFSController;
import fi.tiralabra.game.Controller;
import fi.tiralabra.game.GameEngine;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<Controller> controllerCombo;

    @FXML
    private Button runButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Slider speedSlider;
    private boolean paused;
    private GameEngine ge;
    private Controller con;

    @FXML
    void pause(ActionEvent event) {
        this.paused = !this.paused;
        if (this.paused) {
            pauseButton.setText("Continue");
        } else {
            pauseButton.setText("Pause");
        }
    }

    @FXML
    void changeController(ActionEvent event) {
        Controller controller = this.controllerCombo.getValue();
        if (controller != null) {
            con = controller;
            con.reset();
            ge.setController(con);
        }
    }

    @FXML
    void setSpeed(ActionEvent event) {
        this.ge.setGameRate((int) this.speedSlider.getValue());
        System.out.println(this.speedSlider.getValue());
    }

    @FXML
    void setSpeedClick(MouseEvent event) {
        this.ge.setGameRate((int) this.speedSlider.getValue());
        System.out.println(this.speedSlider.getValue());
    }

    @FXML
    void runPressed(ActionEvent event) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!paused) {
                    ge.cycle();
                    avgScore.setText("Average score: "
                            + String.format("%.2f", ge.avgScore()));
                    avgTime.setText("Average time per apple: " + String.format("%.2f" ,ge.avgTime())
                            + "ms");
                    newScoreValue.setText("Current score: " + ge.getSnake().getScore());
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameRenderer gr = new CanvasGameRenderer(this.canvas);
        ge = new GameEngine(gr);
        con = new AStarController(ge);
        ge.setController(con);
        this.controllerCombo.getItems().add(con);
        this.controllerCombo.getItems().add(new BFSController(ge));
        this.controllerCombo.setValue(this.controllerCombo.getItems().get(0));
        paused = false;
    }

}
