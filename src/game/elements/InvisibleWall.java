package game.elements;

import java.awt.*;

/**
 * Class InvisibleWall
 */
public class InvisibleWall extends Wall {
    /**
     * Constructeur du mur invisible
     * @param size la taille de l'élément
     * @param xPos la position en x de l'élément
     * @param yPos la position en y de l'élément
     */
    public InvisibleWall(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    @Override
    public void render(Graphics2D g) {

    }
}
