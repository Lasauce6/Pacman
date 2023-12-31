package game.elements;

import game.Labyrinth;

import java.awt.*;

/**
 * Class PacGum
 */
public class PacGum extends StaticElement {
    /**
     * Constructeur de PacGum
     * @param size la taille du PacGum
     * @param xPos la position en x du PacGum
     * @param yPos la position en y du PacGum
     */
    public PacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    /**
     * Méthode qui permet de dessiner le PacGum
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(xPos + 3 * size / 8, yPos + 3 * size / 8, size / 4, size / 4);
    }

    /**
     * Méthode qui permet de supprimer le PacGum s'il est mangé
     * @param eaten vrai si le PacGum est mangé, faux sinon
     */
    public void setEaten(boolean eaten) {
        if (eaten) {
            destroy();
            Labyrinth.addScore(20);
        }
    }
}
