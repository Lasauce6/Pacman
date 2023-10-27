package game;

import javax.swing.*;
import java.awt.*;

public class Pacman extends Element {
    private int angle = 45;
    public Pacman(int posX, int posY, int speed, int size, Color color) {
        super(posX, posY, speed, size, Id.PACMAN, color);
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillArc(posX, posY, size, size, angle, 300);
    }
}
