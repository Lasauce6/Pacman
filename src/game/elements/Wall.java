package game.elements;

import java.awt.*;

/**
 * Class Wall
 */
public class Wall extends StaticElement {
    /**
     * Constructeur de Wall
     * @param size la taille du mur
     * @param xPos la position en x du mur
     * @param yPos la position en y du mur
     */
    public Wall(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    /**
     * Méthode qui permet de dessiner le mur
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(xPos, yPos, size, size);
    }
}
