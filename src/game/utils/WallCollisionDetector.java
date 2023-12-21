package game.utils;

import game.Labyrinth;
import game.elements.Element;
import game.elements.Wall;

import java.awt.*;

/**
 * Class WallCollisionDetector
 */
public class WallCollisionDetector {

    /**
     * Méthode qui vérifie si un élément est en collision avec un mur
     * @param element l'élément à vérifier
     * @param dx le déplacement en x
     * @param dy le déplacement en y
     * @return vrai si il y a collision, faux sinon
     */
    public static boolean checkWallCollision(Element element, int dx, int dy) {
        Rectangle r = new Rectangle(element.getxPos() + dx, element.getyPos() + dy, element.getSize(), element.getSize());
        for (Wall w : Labyrinth.getWalls()) {
            if (w.getHitbox().intersects(r)) return true;
        }
        return false;
    }
}
