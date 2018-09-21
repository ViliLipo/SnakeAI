/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import fi.tiralabra.datastructures.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vili
 */
public class Snake implements Cloneable {

    private LinkedList<Location> locations;
    public final static int UP = 1;
    public final static int RIGHT = 2;
    public final static int LEFT = -2;
    public final static int DOWN = -1;
    private int direction;
    private int score;
    private boolean grow;

    private GameArea area;

    /**
     * Create snake. Start direction is right.
     * @param area GameArea of the game
     * @param startx x location on which the snake spawns on
     * @param starty y location on which the snake spawns on
     */
    public Snake(GameArea area, int startx, int starty) {
        this.area = area;
        this.locations = new LinkedList<>();
        this.locations.add(new Location(startx, starty, this.area));
        this.direction = RIGHT;
        this.score = 0;
        this.grow = false;
    }

    /**
     * Turn the snake, cant turn 180 degrees. Fails silently and keeps old
     * direction.
     *
     * @param dir int: 1 = UP, -1 = DOWN, 2 = RIGHT, -2= LEFT
     */
    public void turn(int dir) {
        if (-2 <= dir && dir <= 2 && dir != 0) {
            if (0 != (dir + this.direction)) {
                // System.out.println("Direction : " + this.direction + "dir : " + dir);
                this.direction = dir;
            }
        }
    }

    /**
     * Moves snake next tile in its direction.
     *
     * @return false if snake collides, indicates game over
     */
    public boolean move() {
        switch (this.direction) {
            case UP:
                return this.moveUp();
            case RIGHT:
                return this.moveRight();
            case LEFT:
                return this.moveLeft();
            case DOWN:
                return this.moveDown();
            default:
                return this.moveUp();
        }
    }

    /**
     * Places the next piece of snake above the current head
     *
     * @return false if snake collides, indicates game over
     */
    public boolean moveUp() {
        Location currentHead = this.locations.getLast();
        Location nextHead = new Location(currentHead.getX(),
                currentHead.getY() - 1, this.area);
        return this.moveCore(nextHead);
    }

    /**
     * Place the next piece of snake below the current head
     *
     * @return false if snake collides, indicates game over
     */
    public boolean moveDown() {
        Location currentHead = this.locations.getLast();
        Location nextHead = new Location(currentHead.getX(),
                currentHead.getY() + 1, this.area);
        return this.moveCore(nextHead);
    }

    /**
     * Place next piece of snake right of the current head
     *
     * @return return false if snake collides, indicates game over
     */
    public boolean moveRight() {
        Location currentHead = this.locations.getLast();
        Location nextHead = new Location(currentHead.getX() + 1,
                currentHead.getY(), this.area);
        return this.moveCore(nextHead);
    }

    /**
     * Place next piece of snake right left of the current head
     *
     * @return return false if snake collides, indicates gameover
     */
    public boolean moveLeft() {
        Location currentHead = this.locations.getLast();
        Location nextHead = new Location(currentHead.getX() - 1,
                currentHead.getY(), this.area);
        return this.moveCore(nextHead);
    }

    private boolean moveCore(Location nextHead) {
        boolean success = !this.area.checkCollision(nextHead.getX(), nextHead.getY());
        grow = this.area.checkApple(nextHead.getX(), nextHead.getY());
        if (!grow) {
            Location tail = this.locations.poll();
            this.area.clearLocation(tail);
        } else {
            this.score += 10;
        }
        this.locations.add(nextHead);
        this.area.placeSnakePiece(nextHead);
        return success;
    }

    public boolean getGrow() {
        return grow;
    }

    public int getScore() {
        return this.score;
    }
    /**
     * Get the location of the snakes head
     * @return Location of the snakes head
     */
    public Location getHead() {
        return this.locations.getLast();
    }

    @Override
    public Snake clone() throws CloneNotSupportedException {
        Snake snake = (Snake) super.clone();
        snake.locations = new LinkedList<Location>();
        snake.locations.addAll(this.locations);
        snake.direction = this.direction;
        snake.score = this.score;
        snake.area = this.area.clone();
        return snake;
    }

    public void setArea(GameArea area) {
        this.area = area;
    }

    public GameArea getArea() {
        return this.area;
    }

    public int getDirection() {
        return this.direction;
    }

}
