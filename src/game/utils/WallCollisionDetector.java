package game.utils;

import game.Labyrinth;
import game.elements.Element;
import game.elements.Wall;

import java.awt.*;

public class WallCollisionDetector {
    public static boolean checkWallCollision(Element element, int dx, int dy) {
        Rectangle r = new Rectangle(element.getxPos() + dx, element.getyPos() + dy, element.getSize(), element.getSize());
        for (Wall w : Labyrinth.getWalls()) {
            if (w.getHitbox().intersects(r)) return true;
        }
        return false;
    }
}
