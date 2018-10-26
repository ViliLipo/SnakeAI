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
/**
 * This class Implements UI.
 * 
 * @author vililipo
 */
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
    private int speed = 10;

    private AppTimer at;

    @FXML
    void pause(ActionEvent event) {
        this.at.togglePause();
        this.paused = this.at.getPaused();
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
        this.speed = (int) Math.floor(this.speedSlider.getValue());
        this.at.setSpeed(this.speed);
    }

    @FXML
    void setSpeedClick(MouseEvent event) {
        this.speed = (int) Math.floor(this.speedSlider.getValue());
        this.at.setSpeed(this.speed);
    }

    @FXML
    void runPressed(ActionEvent event) {
        this.at.start();
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
        this.at = new AppTimer(10, ge, avgScore, avgTime, newScoreValue);
    }

}
