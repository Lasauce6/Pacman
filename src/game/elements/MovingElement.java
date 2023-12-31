package game.elements;

import game.Labyrinth;
import game.utils.CollisionDetector;
import game.utils.Direction;
import game.utils.WallCollisionDetector;

import java.awt.*;

/**
 * Class MovingElement
 */
public abstract class MovingElement extends Element {
    protected int speed; // La vitesse de l'élément
    protected int xVel; // La vitesse en x de l'élément
    protected int yVel; // La vitesse en y de l'élément
    protected Direction direction; // La direction de l'élément

    /**
     * Constructeur de MovingElement
     * @param size la taille de l'élément
     * @param xPos la position en x de l'élément
     * @param yPos la position en y de l'élément
     * @param speed la vitesse de l'élément
     */
    public MovingElement(int size, int xPos, int yPos, int speed)  {
        super(size, xPos, yPos);
        this.speed = speed;
    }

    /**
     * Méthode qui met à jour l'élément
     */
    @Override
    public void update()  {
       updatePosition();
    }

    /**
     * Méthode qui permet de mettre à jour la position de l'élément
     */
    public void updatePosition() {
        if (!(xVel == 0 && yVel == 0)) {
            xPos += xVel;
            yPos += yVel;

            // On change la direction
            updateDirection();
        }

        // Si on sort de l'écran, on revient de l'autre côté
        if (xPos > Labyrinth.WIDTH) {
            xPos = -size + speed;
        }
        if (xPos < -size + speed) {
            xPos = Labyrinth.WIDTH;
        }
        if (yPos > Labyrinth.HEIGHT) {
            yPos = -size + speed;
        }
        if (yPos < -size + speed) {
            yPos = Labyrinth.HEIGHT;
        }
    }

    /**
     * Méthode qui vérifie si l'élément est dans la fenêtre de jeu
     * @return vrai si l'élément est dans la fenêtre de jeu, faux sinon
     */
    public boolean onGameplayWindow() {
        return xPos >= 0 || xPos <= Labyrinth.WIDTH || yPos >= 0 || yPos <= Labyrinth.HEIGHT;
    }

    /**
     * Méthode qui permet de mettre à jour la direction de l'élément
     */
    public void updateDirection() {
        if (xVel > 0) {
            direction = Direction.RIGHT;
        } else if (xVel < 0) {
            direction = Direction.LEFT;
        } else if (yVel < 0) {
            direction = Direction.UP;
        } else {
            direction = Direction.DOWN;
        }
    }

    /**
     * Méthode qui permet de récupérer le rectangle de collision
     * @return le rectangle de collision
     */
    @Override
    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    /**
     * Méthode qui permet de récupérer la vitesse de l'élément
     * @return la vitesse de l'élément
     */
    public int getSpd() {
        return speed;
    }

    /**
     * Méthode qui permet de récupérer la vitesse en x de l'élément
     * @return la vitesse en x de l'élément
     */
    public int getxVel() {
        return xVel;
    }

    /**
     * Méthode qui permet de récupérer la vitesse en y de l'élément
     * @return la vitesse en y de l'élément
     */
    public int getyVel() {
        return yVel;
    }

    /**
     * Méthode qui permet de changer la vitesse en x de l'élément
     * @param xVel la nouvelle vitesse en x de l'élément
     */
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    /**
     * Méthode qui permet de changer la vitesse en y de l'élément
     * @param yVel la nouvelle vitesse en y de l'élément
     */
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }
}

