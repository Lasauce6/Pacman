package game.elements;

import game.Labyrinth;

import java.awt.*;

public class PacGum extends StaticElement {
    public PacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(xPos + 3 * size / 8, yPos + 3 * size / 8, size / 4, size / 4);
    }

    public void setEaten(boolean eaten) {
        if (eaten) {
            destroy();
            Labyrinth.addScore(10);
        }
    }
}
