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

/**
 * Classe Pacman
 */
public class Pacman extends MovingElement implements Sujet {
    CollisionDetector collisionDetector; // Le Pacman doit pouvoir détecter les collisions avec les PacGum et les SuperPacGum
    private int angle = 30; // Angle de la bouche du Pacman
    private int arcAngle = 300;
    private final ArrayList<Observer> observers = new ArrayList<>(); // Liste des observateurs
    boolean invisible = false; // Pacman est invisible
    boolean invincible = false; // Pacman est invincible

    /**
     * Constructeur de Pacman
     * @param xPos position en x
     * @param yPos position en y
     * @param speed vitesse
     * @param size taille
     */
    public Pacman(int xPos, int yPos, int speed, int size) {
        super(size, xPos, yPos, speed);
        direction = Direction.RIGHT;
    }

    /**
     * Méthode qui permet de dessiner le Pacman
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {
        if (invisible) g.setColor(new Color(122, 119, 64));
        else if (invincible) g.setColor(Color.ORANGE);
        else g.setColor(Color.YELLOW);
        g.fillArc(xPos, yPos, size, size, angle, 300);
    }

    /**
     * Méthode qui permet de mettre à jour le Pacman
     */
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

    /**
     * Méthode qui permet de changer la direction du Pacman en fonction de la touche pressée
     * @param keyHandler l'objet qui gère les entrées clavier
     */
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

    /**
     * Méthode qui ajoute un détecteur de collision au Pacman
     * @param collisionDetector le détecteur de collision
     */
    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    /**
     * Méthode qui ajoute un observateur au Pacman
     * @param observer l'observateur
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Méthode qui supprime un observateur du Pacman
     * @param observer l'observateur
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Méthode qui notifie les observateurs que le Pacman a mangé un PacGum
     * @param pg le PacGum mangé
     */
    @Override
    public void notifyObserverPacGumEaten(PacGum pg) {
        for (Observer observer : observers) {
            observer.updatePacGumEaten(pg);
        }
    }

    /**
     * Méthode qui notifie les observateurs que le Pacman a mangé un SuperPacGum
     * @param spg le SuperPacGum mangé
     */
    @Override
    public void notifyObserverSuperPacGumEaten(SuperPacGum spg) {
        for (Observer observer : observers) {
            observer.updateSuperPacGumEaten(spg);
        }
    }

    /**
     * Méthode qui notifie les observateurs que le Pacman a eu une collision avec un fantôme
     * @param g le fantôme
     */
    @Override
    public void notifyObserverGhostCollision(Ghost g) {
        for (Observer observer : observers) {
            observer.updateGhostCollision(g);
        }
    }

    /**
     * Méthode qui notifie les observateurs que le Pacman est invisible
     */
    @Override
    public void notifyObserverPacmanInvisible() {

    }

    /**
     * Méthode qui notifie les observateurs que le Pacman est invincible
     */
    @Override
    public void notifyObserverPacmanInvincible() {

    }

    /**
     * Méthode qui notifie les observateurs que le labyrinthe a changé
     */
    @Override
    public void notifyObserverLabyrinthChange() {

    }

    /**
     * Méthode qui permet de rendre le Pacman invisible
     * @param b true si le Pacman doit être invisible, false sinon
     */
    public void setInvisible(boolean b) {
        invisible = b;
    }

    /**
     * Méthode qui permet de rendre le Pacman invincible
     * @param b true si le Pacman doit être invincible, false sinon
     */
    public void setInvincible(boolean b) {
        invincible = b;
    }

}
