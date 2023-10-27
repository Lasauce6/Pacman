package game;

import java.awt.*;

public class Case extends Element {
    public Case(int posX, int posY, int speed, int size, Id id, Color color) {
        super(posX, posY, speed, size, id, color);
    }

    @Override
    public void draw(Graphics g) {
        switch (id) {
            case WALL:
                g.setColor(color);
                g.fillRect(posX, posY, size, size);
                break;
            case PACGOMME:
                g.setColor(color);
                g.fillOval(posX, posY, size / 2, size / 2);
                break;
            case POINT:
                g.setColor(Color.BLACK);
                g.fillRect(posX, posY, size, size);
                g.setColor(Color.WHITE);
                g.fillOval(posX + 3 * size / 8, posY + 3 * size / 8, size / 4, size / 4);
                break;
        }
    }
}
