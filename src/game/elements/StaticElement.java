package game.elements;

import java.awt.*;

/**
 * Class StaticElement
 */
public abstract class StaticElement extends Element {

    protected Rectangle hitbox; // Le rectangle de collision

    /**
     * Constructeur de StaticElement
     * @param size la taille de l'élément
     * @param xPos la position en x de l'élément
     * @param yPos la position en y de l'élément
     */
    public StaticElement(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
        hitbox = new Rectangle(xPos, yPos, size, size);
    }

    /**
     * Méthode qui permet de récupérer le rectangle de collision
     * @return le rectangle de collision
     */
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Méthode qui permet de dessiner l'élément
     * @param g l'objet graphique
     */
    public abstract void render(Graphics2D g);
}
