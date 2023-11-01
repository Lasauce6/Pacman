package game.utils;

import game.Labyrinth;
import game.elements.Element;

public record CollisionDetector(Labyrinth labyrinth) {

    public Element checkCollision(Element element, Class<? extends Element> collisionCheck) {
        for (Element e : labyrinth.getListElements()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().contains(element.getxPos() + element.getSize() / 2, element.getyPos() + element.getSize() / 2))
                return e;
        }
        return null;
    }
}
