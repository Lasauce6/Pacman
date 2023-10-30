package game.elements;

import game.Labyrinth;
import game.utils.CollisionDetector;
import game.utils.Direction;
import game.utils.KeyHandler;
import game.utils.WallCollisionDetector;

import java.awt.*;

public class Pacman extends MovingElement {
    CollisionDetector collisionDetector;
    private int angle = 30;
    private int arcAngle = 300;
    public Pacman(int xPos, int yPos, int speed, int size) {
        super(size, xPos, yPos, speed);
        direction = Direction.RIGHT;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillArc(xPos, yPos, size, size, angle, 300);
    }

    @Override
    public void update() {
        switch (direction) {
            case UP -> angle = 110;
            case DOWN -> angle = 300;
            case LEFT -> angle = 210;
            case RIGHT -> angle = 30;
        }
        if (arcAngle == 300) {
            arcAngle = 350;
        } else {
            arcAngle = 300;
        }

        PacGum pg = (PacGum) collisionDetector.checkCollision(this, PacGum.class);
        if (pg != null) {
            pg.setEaten(true);
        }

        if (!WallCollisionDetector.checkWallCollision(this, xVel, yVel)) {
            updatePosition();
        }
    }

    public void input(KeyHandler keyHandler) {
        int newXVel = 0;
        int newYVel = 0;

        if (!onGameplayWindow()) return;

        if (keyHandler.k_left.isPressed && xVel >= 0 && !WallCollisionDetector.checkWallCollision(this, -speed, 0)) {
            newXVel = -speed;
        }
        if (keyHandler.k_right.isPressed && xVel <= 0 && !WallCollisionDetector.checkWallCollision(this, speed, 0)) {
            newXVel = speed;
        }
        if (keyHandler.k_up.isPressed && yVel >= 0 && !WallCollisionDetector.checkWallCollision(this, 0, -speed)) {
            newYVel = -speed;
        }
        if (keyHandler.k_down.isPressed && yVel <= 0 && !WallCollisionDetector.checkWallCollision(this, 0, speed)) {
            newYVel = speed;
        }

        if (newYVel == 0 && newXVel == 0) return;
        if (!Labyrinth.getFirstInput()) Labyrinth.setFirstInput(true);


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
    }

    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }
}
