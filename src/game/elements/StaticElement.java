package game.elements;

import java.awt.*;

public abstract class StaticElement extends Element {

    protected Rectangle hitbox;

    public StaticElement(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
        hitbox = new Rectangle(xPos, yPos, size, size);
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    public abstract void render(Graphics2D g);
}
