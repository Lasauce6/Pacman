package game.elements;

import java.awt.*;

/**
 * Class EmptyCase
 */
public class EmptyCase extends StaticElement {

    /**
     * Constructeur de EmptyCase
     * @param size la taille de la case vide
     * @param xPos la position en x de la case vide
     * @param yPos la position en y de la case vide
     */
    public EmptyCase(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    /**
     * Méthode qui permet de dessiner la case vide
     * @param g l'objet graphique
     */
    @Override
    public void render(Graphics2D g) {}
}
