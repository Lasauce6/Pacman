package game.elements;

import java.awt.*;

public class Wall extends StaticElement {
    public Wall(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(xPos, yPos, size, size);
    }
}
