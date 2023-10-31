package game.elements.superpacgum;

import game.elements.StaticElement;

import java.awt.*;

public abstract class SuperPacGum extends StaticElement {
    private final Color color;
    public SuperPacGum(int size, int xPos, int yPos, Color color) {
        super(size, xPos, yPos);
        this.color = color;
    }
    public abstract void setEaten(boolean b);

    @Override
    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval(xPos + 3, yPos + 3, size - 6, size - 6);
    }
}
