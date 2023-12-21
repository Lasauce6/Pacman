package game.utils;

import game.Labyrinth;
import game.elements.Element;

/**
 * Class CollisionDetector
 */
public record CollisionDetector(Labyrinth labyrinth) {

    /**
     * Méthode qui vérifie si un élément est en collision avec un autre élément
     * @param element l'élément à vérifier
     * @param collisionCheck le type d'élément avec lequel vérifier la collision
     * @return l'élément avec lequel il y a collision, null sinon
     */
    public Element checkCollision(Element element, Class<? extends Element> collisionCheck) {
        for (Element e : labyrinth.getListElements()) {
            if (!e.isDestroyed() && collisionCheck.isInstance(e) && e.getHitbox().contains(element.getxPos() + element.getSize() / 2, element.getyPos() + element.getSize() / 2))
                return e;
        }
        return null;
    }
}
