package game;

import javax.swing.*;
import java.awt.*;

public abstract class Element {
    protected int posX, posY, speed;
    protected int size;
    protected Id id;
    protected Color color;

    public Element(int posX, int posY, int speed, int size, Id id, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.size = size;
        this.id = id;
        this.color = color;
    }

    public void draw(Graphics g) {
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
