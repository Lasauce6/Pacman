package game.elements;

import java.awt.*;

public class EmptyCase extends StaticElement {

    public EmptyCase(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
    }

    @Override
    public void render(Graphics2D g) {}
}
