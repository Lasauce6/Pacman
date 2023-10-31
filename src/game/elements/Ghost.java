package game.elements;

import game.Labyrinth;
import game.utils.Direction;
import game.utils.WallCollisionDetector;

import java.awt.*;
import java.util.ArrayList;

public class Ghost extends MovingElement {
    private final Color color;
    private boolean isVulnerable = false;
    private boolean isEaten = false;

    public Ghost(int size, int xPos, int yPos, int speed, int xVel, int yVel, Color color) {
        super(size, xPos, yPos, speed);
        this.xVel = xVel;
        this.yVel = yVel;
        this.color = color;
        updateDirection();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval(xPos, yPos, size, size);
    }

    @Override
    public void update() {
        if (!Labyrinth.getFirstInput()) return; // Les fantômes ne bougent pas tant que le joueur n'a pas joué
        if (isEaten) {
            goToBase();
            return;
        }

        int newXVel = 0;
        int newYVel = 0;

        if (!onGameplayWindow()) return;

        if (direction == Direction.LEFT && !WallCollisionDetector.checkWallCollision(this, -speed, 0)) {
            newXVel = -speed;
        }
        if (direction == Direction.RIGHT && !WallCollisionDetector.checkWallCollision(this, speed, 0)) {
            newXVel = speed;
        }
        if (direction == Direction.UP && !WallCollisionDetector.checkWallCollision(this, 0, -speed)) {
            newYVel = -speed;
        }
        if (direction == Direction.DOWN && !WallCollisionDetector.checkWallCollision(this, 0, speed)) {
            newYVel = speed;
        }

        if (newYVel == 0 && newXVel == 0) findNewDirection();

        if (Math.abs(newXVel) != Math.abs(newYVel)) {
            xVel = newXVel;
            yVel = newYVel;
        } else {
            if (xVel != 0) {
                xVel = 0;
                yVel = newYVel;
            } else {
                xVel = newXVel;
                yVel = 0;
            }
        }

        if (!WallCollisionDetector.checkWallCollision(this, xVel, yVel)) updatePosition();
    }

    private void findNewDirection() {
        ArrayList<Direction> possibleDirections = new ArrayList<>();

        switch (direction) {
            case UP -> {
                if (!WallCollisionDetector.checkWallCollision(this, -speed, 0)) possibleDirections.add(Direction.LEFT);
                if (!WallCollisionDetector.checkWallCollision(this, speed, 0)) possibleDirections.add(Direction.RIGHT);
                if (!WallCollisionDetector.checkWallCollision(this, 0, speed)) possibleDirections.add(Direction.DOWN);
            }
            case DOWN -> {
                if (!WallCollisionDetector.checkWallCollision(this, -speed, 0)) possibleDirections.add(Direction.LEFT);
                if (!WallCollisionDetector.checkWallCollision(this, speed, 0)) possibleDirections.add(Direction.RIGHT);
                if (!WallCollisionDetector.checkWallCollision(this, 0, -speed)) possibleDirections.add(Direction.UP);
            }
            case LEFT -> {
                if (!WallCollisionDetector.checkWallCollision(this, 0, -speed)) possibleDirections.add(Direction.UP);
                if (!WallCollisionDetector.checkWallCollision(this, 0, speed)) possibleDirections.add(Direction.DOWN);
                if (!WallCollisionDetector.checkWallCollision(this, speed, 0)) possibleDirections.add(Direction.RIGHT);
            }
            case RIGHT -> {
                if (!WallCollisionDetector.checkWallCollision(this, 0, -speed)) possibleDirections.add(Direction.UP);
                if (!WallCollisionDetector.checkWallCollision(this, 0, speed)) possibleDirections.add(Direction.DOWN);
                if (!WallCollisionDetector.checkWallCollision(this, -speed, 0)) possibleDirections.add(Direction.LEFT);
            }
        }

        direction = possibleDirections.get((int) (Math.random() * possibleDirections.size()));

        switch (direction) {
            case UP -> {
                xVel = 0;
                yVel = -speed;
            }
            case DOWN -> {
                xVel = 0;
                yVel = speed;
            }
            case LEFT -> {
                xVel = -speed;
                yVel = 0;
            }
            case RIGHT -> {
                xVel = speed;
                yVel = 0;
            }
        }

    }

    private void goToBase() {
        if (xPos == 25 * size && yPos == 14 * size) {
            isEaten = false;
            return;
        }

        if (xPos < 25 * size) {
            xVel = speed;
            yVel = 0;
        } else if (xPos > 25 * size) {
            xVel = -speed;
            yVel = 0;
        } else if (yPos < 14 * size) {
            xVel = 0;
            yVel = speed;
        } else if (yPos > 14 * size) {
            xVel = 0;
            yVel = -speed;
        }

        if (!WallCollisionDetector.checkWallCollision(this, xVel, yVel)) updatePosition();
    }

    public boolean isVulnerable() {
        return isVulnerable;
    }

    public void setVulnerable() {
        isVulnerable = true;
    }

    public void setEaten(boolean b) {
        isEaten = b;
    }
}
