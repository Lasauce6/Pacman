package game.elements;

import java.awt.*;

/**
 * Class InvisibleWall
 */
public class InvisibleWall extends Wall {
    /**
     * Constructeur du mur invisible
     * @param sizeX la longueur du mur
     * @param sizeY la largeur du mur
     * @param xPos la position en x de l'élément
     * @param yPos la position en y de l'élément
     */
    public InvisibleWall(int sizeX, int sizeY, int xPos, int yPos) {
        super(Math.max(sizeX, sizeY), xPos, yPos);
        hitbox = new Rectangle(xPos, yPos, sizeX, sizeY);
    }

    @Override
    public void render(Graphics2D g) {
    }
}
