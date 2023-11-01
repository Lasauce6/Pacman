package game.elements.superpacgum;

import game.Labyrinth;
import game.Observer;

import java.awt.*;

public class OrangePacGum extends SuperPacGum {
    public OrangePacGum(int size, int xPos, int yPos) {
        super(size, xPos, yPos, Color.ORANGE);
    }

    @Override
    public void setEaten(boolean b) {
        if (b) {
            destroy();
            Labyrinth.addScore(500);
            notifyObserverPacmanInvincible();
        }

    }

    @Override
    public void notifyObserverPacmanInvisible() {

    }

    @Override
    public void notifyObserverPacmanInvincible() {
        for (Observer observer : observers) {
            observer.updatePacmanInvincible();
        }
    }

    @Override
    public void notifyObserverLabyrinthChange() {

    }
}
