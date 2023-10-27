package game;

import javax.swing.*;
import java.awt.*;

public class Ghost extends Element {

    public Ghost(int posX, int posY, int speed, int size, Color color) {
        super(posX, posY, speed, size, Id.GHOST, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(posX, posY, size, size);
    }
}
