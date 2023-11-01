package game.elements;

import game.Labyrinth;
import game.Observer;
import game.Sujet;
import game.elements.superpacgum.SuperPacGum;
import game.utils.CollisionDetector;
import game.utils.Direction;
import game.utils.KeyHandler;
import game.utils.WallCollisionDetector;

import java.awt.*;
import java.util.ArrayList;

public class Pacman extends MovingElement implements Sujet {
    CollisionDetector collisionDetector;
    private int angle = 30;
    private int arcAngle = 300;
    private final ArrayList<Observer> observers = new ArrayList<>();
    boolean invisible = false;
    boolean invincible = false;


    public Pacman(int xPos, int yPos, int speed, int size) {
        super(size, xPos, yPos, speed);
        direction = Direction.RIGHT;
    }

    @Override
    public void render(Graphics2D g) {
        if (invisible) g.setColor(new Color(122, 119, 64));
        else if (invincible) g.setColor(Color.ORANGE);
        else g.setColor(Color.YELLOW);
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
            notifyObserverPacGumEaten(pg);
        }

        SuperPacGum spg = (SuperPacGum) collisionDetector.checkCollision(this, SuperPacGum.class);
        if (spg != null) {
            notifyObserverSuperPacGumEaten(spg);
        }

        Ghost g = (Ghost) collisionDetector.checkCollision(this, Ghost.class);
        if (g != null && !invisible) {
            notifyObserverGhostCollision(g);
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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserverPacGumEaten(PacGum pg) {
        for (Observer observer : observers) {
            observer.updatePacGumEaten(pg);
        }
    }

    @Override
    public void notifyObserverSuperPacGumEaten(SuperPacGum spg) {
        for (Observer observer : observers) {
            observer.updateSuperPacGumEaten(spg);
        }
    }

    @Override
    public void notifyObserverGhostCollision(Ghost g) {
        for (Observer observer : observers) {
            observer.updateGhostCollision(g);
        }
    }

    @Override
    public void notifyObserverPacmanInvisible() {

    }

    @Override
    public void notifyObserverPacmanInvincible() {

    }

    @Override
    public void notifyObserverLabyrinthChange() {

    }

    public void setInvisible(boolean b) {
        invisible = b;
    }

    public void setInvincible(boolean b) {
        invincible = b;
    }

}
