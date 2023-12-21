package game.elements;

import game.Labyrinth;
import game.utils.Direction;
import game.utils.Utils;
import game.utils.WallCollisionDetector;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class Ghost
 */
public class Ghost extends MovingElement {
    private final Color color; // La couleur du fantôme
    private boolean isVulnerable = false; // Le fantôme est vulnérable
    private boolean isEaten = false; // Le fantôme est mangé
    private final int vulnerableSpeed; // La vitesse du fantôme quand il est vulnérable

    /**
     * Constructeur de Ghost
     * @param size la taille du fantôme
     * @param xPos la position en x du fantôme
     * @param yPos la position en y du fantôme
     * @param speed la vitesse du fantôme
     * @param xVel la vitesse en x du fantôme
     * @param yVel la vitesse en y du fantôme
     * @param color la couleur du fantôme
     */
    public Ghost(int size, int xPos, int yPos, int speed, int xVel, int yVel, Color color) {
        super(size, xPos, yPos, speed);
        this.xVel = xVel;
        this.yVel = yVel;
        this.color = color;
        this.vulnerableSpeed = speed / 2;
        updateDirection();
    }

    /**
     * Méthode qui permet de créer l'affichage du fantôme
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {
        if (isEaten) g.setColor(Color.WHITE);
        else if (isVulnerable) g.setColor(Color.BLUE);
        else g.setColor(color);
        g.fillOval(xPos, yPos, size, size);
    }

    /**
     * Méthode qui permet de mettre à jour le fantôme
     */
    @Override
    public void update() {
        if (!Labyrinth.getFirstInput()) return; // Les fantômes ne bougent pas tant que le joueur n'a pas joué
        if (isEaten) {
            goToBase();
            if (xPos == 14 * size && yPos == 14 * size) {
                isEaten = false;
                isVulnerable = false;
            }
            return;
        }
        int actualSpeed = speed;
        if (isVulnerable) actualSpeed = vulnerableSpeed;

        int newXVel = 0;
        int newYVel = 0;

        if (!onGameplayWindow()) return;

        if (direction == Direction.LEFT && !WallCollisionDetector.checkWallCollision(this, -actualSpeed, 0)) {
            newXVel = -actualSpeed;
        }
        if (direction == Direction.RIGHT && !WallCollisionDetector.checkWallCollision(this, actualSpeed, 0)) {
            newXVel = actualSpeed;
        }
        if (direction == Direction.UP && !WallCollisionDetector.checkWallCollision(this, 0, -actualSpeed)) {
            newYVel = -actualSpeed;
        }
        if (direction == Direction.DOWN && !WallCollisionDetector.checkWallCollision(this, 0, actualSpeed)) {
            newYVel = actualSpeed;
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

    /**
     * Méthode qui permet de mettre à jour la direction du fantôme
     */
    private void findNewDirection() {
        int actualSpeed = speed;
        if (isVulnerable) actualSpeed = vulnerableSpeed;
        ArrayList<Direction> possibleDirections = new ArrayList<>();

        switch (direction) {
            case UP -> {
                if (!WallCollisionDetector.checkWallCollision(this, -actualSpeed, 0)) possibleDirections.add(Direction.LEFT);
                if (!WallCollisionDetector.checkWallCollision(this, actualSpeed, 0)) possibleDirections.add(Direction.RIGHT);
                if (!WallCollisionDetector.checkWallCollision(this, 0, actualSpeed)) possibleDirections.add(Direction.DOWN);
            }
            case DOWN -> {
                if (!WallCollisionDetector.checkWallCollision(this, -actualSpeed, 0)) possibleDirections.add(Direction.LEFT);
                if (!WallCollisionDetector.checkWallCollision(this, actualSpeed, 0)) possibleDirections.add(Direction.RIGHT);
                if (!WallCollisionDetector.checkWallCollision(this, 0, -actualSpeed)) possibleDirections.add(Direction.UP);
            }
            case LEFT -> {
                if (!WallCollisionDetector.checkWallCollision(this, 0, -actualSpeed)) possibleDirections.add(Direction.UP);
                if (!WallCollisionDetector.checkWallCollision(this, 0, actualSpeed)) possibleDirections.add(Direction.DOWN);
                if (!WallCollisionDetector.checkWallCollision(this, actualSpeed, 0)) possibleDirections.add(Direction.RIGHT);
            }
            case RIGHT -> {
                if (!WallCollisionDetector.checkWallCollision(this, 0, -actualSpeed)) possibleDirections.add(Direction.UP);
                if (!WallCollisionDetector.checkWallCollision(this, 0, actualSpeed)) possibleDirections.add(Direction.DOWN);
                if (!WallCollisionDetector.checkWallCollision(this, -actualSpeed, 0)) possibleDirections.add(Direction.LEFT);
            }
        }

        direction = possibleDirections.get((int) (Math.random() * possibleDirections.size()));

        switch (direction) {
            case UP -> {
                xVel = 0;
                yVel = -actualSpeed;
            }
            case DOWN -> {
                xVel = 0;
                yVel = actualSpeed;
            }
            case LEFT -> {
                xVel = -actualSpeed;
                yVel = 0;
            }
            case RIGHT -> {
                xVel = actualSpeed;
                yVel = 0;
            }
        }

    }

    /**
     * Méthode qui permet de faire aller le fantôme à la base
     */
    private void goToBase() {
        int targetX = 14 * size;
        int targetY = 14 * size;

        int newXVel = 0;
        int newYVel = 0;

        double minDist = Double.MAX_VALUE;

        if (xVel <= 0 && !WallCollisionDetector.checkWallCollision(this, -speed, 0)) {
            double distance = Utils.getDistance(xPos - speed, yPos, targetX, targetY);
            if (distance < minDist) {
                newXVel = -speed;
                minDist = distance;
            }
        }
        if (xVel >= 0 && !WallCollisionDetector.checkWallCollision(this, speed, 0)) {
            double distance = Utils.getDistance(xPos + speed, yPos,  targetX, targetY);
            if (distance < minDist) {
                newXVel = speed;
                minDist = distance;
            }
        }
        if (yVel <= 0 && !WallCollisionDetector.checkWallCollision(this, 0, -speed)) {
            double distance = Utils.getDistance(xPos, yPos - speed, targetX, targetY);
            if (distance < minDist) {
                newXVel = 0;
                newYVel = -speed;
                minDist = distance;
            }
        }
        if (yVel >= 0 && !WallCollisionDetector.checkWallCollision(this, 0, speed)) {
            double distance = Utils.getDistance(xPos, yPos + speed, targetX, targetY);
            if (distance < minDist) {
                newXVel = 0;
                newYVel = speed;
            }
        }

        if (newXVel == 0 && newYVel == 0) return;

        xVel = (newXVel);
        yVel = (newYVel);

        if (!WallCollisionDetector.checkWallCollision(this, xVel, yVel)) updatePosition();
    }

    /**
     * Méthode qui permet de récupérer si le fantôme est vulnérable
     * @return vrai si le fantôme est vulnérable, faux sinon
     */
    public boolean isVulnerable() {
        return isVulnerable;
    }

    /**
     * Méthode qui permet de changer la vulnérabilité du fantôme
     * @param b vrai si le fantôme est vulnérable, faux sinon
     */
    public void setVulnerable(boolean b) {
        if (isVulnerable == b) return;
        isVulnerable = b;
    }

    /**
     * Méthode qui permet de changer si le fantôme est mangé
     * @param b vrai si le fantôme est mangé, faux sinon
     */
    public void setEaten(boolean b) {
        isEaten = b;
    }
}
