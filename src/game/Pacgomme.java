package game;

import javax.swing.*;
import java.awt.*;

public class Pacgomme extends Element {
    public Pacgomme(int posX, int posY, int speed, int size, Color color) {
        super(posX, posY, speed, size, Id.PACGOMME, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(posX, posY, size, size);
    }
}
