package game.elements.superpacgum;

import game.Observer;
import game.Sujet;
import game.elements.StaticElement;

import java.awt.*;
import java.util.ArrayList;

public abstract class SuperPacGum extends StaticElement implements Sujet {
    final ArrayList<Observer> observers = new ArrayList<>();

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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserverPacGumEaten(game.elements.PacGum pg) {
    }

    @Override
    public void notifyObserverSuperPacGumEaten(SuperPacGum spg) {
    }

    @Override
    public void notifyObserverGhostCollision(game.elements.Ghost g) {
    }
}
